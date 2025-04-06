import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateDeleteOrder {
    public UpdateDeleteOrder(Object[] rowData, Orders ordersInstance) { // Accept Orders instance
        JFrame frame = new JFrame("Update/Delete Order");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Extract data from rowData
        int orderId = (int) rowData[0];
        String currentStatus = (String) rowData[4];

        // Fetch the current quantity from the order_items table
        int currentQuantity = 0;
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT quantity_ordered FROM order_items WHERE order_id = ?")) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currentQuantity = rs.getInt("quantity_ordered");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to fetch order quantity. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            return;
        }

        // Status Section
        JLabel statusLabel = new JLabel("Update Status:");
        JRadioButton pendingButton = new JRadioButton("Pending", currentStatus.equals("Pending"));
        JRadioButton completedButton = new JRadioButton("Completed", currentStatus.equals("Completed"));
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(pendingButton);
        statusGroup.add(completedButton);
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);
        statusPanel.add(pendingButton);
        statusPanel.add(completedButton);
        frame.add(statusPanel);

        // Quantity Section
        JLabel quantityLabel = new JLabel("Update Quantity:");
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(currentQuantity, 1, 1000, 1));
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        frame.add(quantityPanel);

        // Buttons
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        frame.add(buttonPanel);

        // Action listener for Update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newStatus = pendingButton.isSelected() ? "Pending" : "Completed";
                int newQuantity = (int) quantitySpinner.getValue();

                // Update the database
                try (Connection conn = DatabaseConnection.connect()) {
                    if (conn == null) {
                        JOptionPane.showMessageDialog(frame, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Update orders table
                    String updateOrderQuery = "UPDATE orders SET status = ? WHERE order_id = ?";
                    try (PreparedStatement updateOrderStmt = conn.prepareStatement(updateOrderQuery)) {
                        updateOrderStmt.setString(1, newStatus);
                        updateOrderStmt.setInt(2, orderId);
                        updateOrderStmt.executeUpdate();
                    }

                    // Update order_items table
                    String updateOrderItemQuery = "UPDATE order_items SET quantity_ordered = ? WHERE order_id = ?";
                    try (PreparedStatement updateOrderItemStmt = conn.prepareStatement(updateOrderItemQuery)) {
                        updateOrderItemStmt.setInt(1, newQuantity);
                        updateOrderItemStmt.setInt(2, orderId);
                        updateOrderItemStmt.executeUpdate();
                    }

                    // If the status is "Completed", update the products table
                    if (newStatus.equals("Completed")) {
                        String updateProductQuery = "UPDATE products p " +
                                "JOIN order_items oi ON p.product_id = oi.product_id " +
                                "SET p.stock_quantity = p.stock_quantity + oi.quantity_ordered " +
                                "WHERE oi.order_id = ?";
                        try (PreparedStatement updateProductStmt = conn.prepareStatement(updateProductQuery)) {
                            updateProductStmt.setInt(1, orderId);
                            updateProductStmt.executeUpdate();
                        }
                    }

                    // Success message
                    JOptionPane.showMessageDialog(frame, "Order updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh table data in Orders.java
                    ordersInstance.refreshTableData();

                    // Close the form
                    frame.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to update the order. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for Cancel button
        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
