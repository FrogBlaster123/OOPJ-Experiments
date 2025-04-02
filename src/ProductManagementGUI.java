import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductManagementGUI extends JFrame {
    private static DefaultTableModel tableModel;
    private static JComboBox<String> productComboBox;
    private static JTextField updateStockText;
    private static JTextField updateReorderText;
    private static JTextField updatePriceText;
    private static JTable deleteTable;
    private static int userId;

    public ProductManagementGUI() {
        setTitle("Product Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Remove the label and add the main panel with menu and card layout
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem addProductMenuItem = new JMenuItem("AddProduct");
        JMenuItem updateProductMenuItem = new JMenuItem("Update");
        JMenuItem deleteProductMenuItem = new JMenuItem("Delete");

        menu.add(addProductMenuItem);
        menu.add(updateProductMenuItem);
        menu.add(deleteProductMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new CardLayout());
        JPanel addProductPanel = createAddProductPanel();
        JPanel updateProductPanel = createUpdateProductPanel();
        JPanel deleteProductPanel = createDeleteProductPanel();

        mainPanel.add(addProductPanel, "AddProduct");
        mainPanel.add(updateProductPanel, "Update");
        mainPanel.add(deleteProductPanel, "Delete");

        addProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "AddProduct");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        updateProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "Update");
            loadProductNames();
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        deleteProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "Delete");
            updateDeleteTable();
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        add(mainPanel);
    }

    public static void setUserId(int userId) {
        ProductManagementGUI.userId = userId;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Product Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem addProductMenuItem = new JMenuItem("AddProduct");
        JMenuItem updateProductMenuItem = new JMenuItem("Update");
        JMenuItem deleteProductMenuItem = new JMenuItem("Delete");

        menu.add(addProductMenuItem);
        menu.add(updateProductMenuItem);
        menu.add(deleteProductMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new CardLayout());
        JPanel addProductPanel = createAddProductPanel();
        JPanel updateProductPanel = createUpdateProductPanel();
        JPanel deleteProductPanel = createDeleteProductPanel();

        mainPanel.add(addProductPanel, "AddProduct");
        mainPanel.add(updateProductPanel, "Update");
        mainPanel.add(deleteProductPanel, "Delete");

        addProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "AddProduct");
            mainPanel.revalidate(); // Ensure the panel layout is updated
            mainPanel.repaint();    // Ensure the panel is redrawn
            System.out.println("Switched to AddProduct panel");
        });

        updateProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "Update");
            loadProductNames();     // Ensure product names are loaded
            mainPanel.revalidate(); // Ensure the panel layout is updated
            mainPanel.repaint();    // Ensure the panel is redrawn
            System.out.println("Switched to Update panel");
        });

        deleteProductMenuItem.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "Delete");
            updateDeleteTable();    // Ensure the delete table is updated
            mainPanel.revalidate(); // Ensure the panel layout is updated
            mainPanel.repaint();    // Ensure the panel is redrawn
            System.out.println("Switched to Delete panel");
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createAddProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for responsiveness
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel nameLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
    
        JTextField nameText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameText, gbc);
    
        JLabel stockLabel = new JLabel("Stock:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(stockLabel, gbc);
    
        JTextField stockText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(stockText, gbc);
    
        JLabel reorderLabel = new JLabel("Reorder Level:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(reorderLabel, gbc);
    
        JTextField reorderText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(reorderText, gbc);
    
        JLabel priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(priceLabel, gbc);
    
        JTextField priceText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(priceText, gbc);
    
        JButton addButton = new JButton("Add Product");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            String name = nameText.getText();
            int stock;
            int reorderLevel;
            double price;
        
            // Validate input
            try {
                stock = Integer.parseInt(stockText.getText());
                reorderLevel = Integer.parseInt(reorderText.getText());
                price = Double.parseDouble(priceText.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric values for stock, reorder level, and price.");
                return;
            }
        
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
                return;
            }
        
            // Call the method to add the product to the database
            addProductToDatabase(name, stock, reorderLevel, price);
            JOptionPane.showMessageDialog(null, "Product added successfully!");
        
            // Refresh the table
            System.out.println("Updating table...");
            updateTable();
        });
    
        // Table to display products
        tableModel = new DefaultTableModel(new String[]{"Name", "Stock", "Reorder Level", "Price"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
    
        // Load initial data
        updateTable();
    
        return panel;
    }

    private static JPanel createUpdateProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for responsiveness
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel selectProductLabel = new JLabel("Select Product:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectProductLabel, gbc);
    
        productComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(productComboBox, gbc);
    
        JLabel updateStockLabel = new JLabel("Stock:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(updateStockLabel, gbc);
    
        updateStockText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(updateStockText, gbc);
    
        JLabel updateReorderLabel = new JLabel("Reorder Level:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateReorderLabel, gbc);
    
        updateReorderText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(updateReorderText, gbc);
    
        JLabel updatePriceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(updatePriceLabel, gbc);
    
        updatePriceText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(updatePriceText, gbc);
    
        JButton updateButton = new JButton("Update Product");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            String name = (String) productComboBox.getSelectedItem();
            int stock;
            int reorderLevel;
            double price;
        
            // Validate input
            try {
                stock = Integer.parseInt(updateStockText.getText());
                reorderLevel = Integer.parseInt(updateReorderText.getText());
                price = Double.parseDouble(updatePriceText.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric values for stock, reorder level, and price.");
                return;
            }
        
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a product to update.");
                return;
            }
        
            // Call the method to update the product in the database
            updateProductInDatabase(name, stock, reorderLevel, price);
            JOptionPane.showMessageDialog(null, "Product updated successfully!");
        
            // Refresh the table
            updateTable();
        });
    
        // Table to display products
        tableModel = new DefaultTableModel(new String[]{"Name", "Stock", "Reorder Level", "Price"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
    
        // Load initial data
        updateTable();
    
        return panel;
    }

    private static JPanel createDeleteProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for responsiveness
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // Table to display products
        DefaultTableModel deleteTableModel = new DefaultTableModel(new String[]{"Name", "Stock", "Reorder Level", "Price"}, 0);
        deleteTable = new JTable(deleteTableModel);
        JScrollPane scrollPane = new JScrollPane(deleteTable);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);
    
        JButton deleteButton = new JButton("Delete Product");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(deleteButton, gbc);
    
        deleteButton.addActionListener(e -> {
            int selectedRow = deleteTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedProduct = (String) deleteTable.getValueAt(selectedRow, 0);
                deleteProductFromDatabase(selectedProduct);
                JOptionPane.showMessageDialog(null, "Product Deleted Successfully!");
                updateDeleteTable();
                updateTable();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a product to delete.");
            }
        });
    
        return panel;
    }

    private static void addProductToDatabase(String name, int stock, int reorderLevel, double price) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO products (user_id, name, stock_quantity, reorder_level, price) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, userId); // Use the logged-in user's ID
            stmt.setString(2, name);
            stmt.setInt(3, stock);
            stmt.setInt(4, reorderLevel);
            stmt.setDouble(5, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL error occurred while adding product to database.");
            e.printStackTrace();
        }
    }

    private static void updateProductInDatabase(String name, int stock, int reorderLevel, double price) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE products SET stock_quantity = ?, reorder_level = ?, price = ? WHERE name = ?")) {
            stmt.setInt(1, stock);
            stmt.setInt(2, reorderLevel);
            stmt.setDouble(3, price);
            stmt.setString(4, name);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while updating product in database.");
            e.printStackTrace();
        }
    }

    private static void deleteProductFromDatabase(String name) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM products WHERE name = ? AND user_id = ?")) {
            stmt.setString(1, name);
            stmt.setInt(2, userId); // Ensure only the logged-in user's product is deleted
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL error occurred while deleting product from database.");
            e.printStackTrace();
        }
    }

    private static void updateTable() {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT name, stock_quantity, reorder_level, price FROM products")) {
            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                String name = rs.getString("name");
                int stock = rs.getInt("stock_quantity");
                int reorderLevel = rs.getInt("reorder_level");
                double price = rs.getDouble("price");
                tableModel.addRow(new Object[]{name, stock, reorderLevel, price});
            tableModel.fireTableDataChanged();
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while fetching products from database.");
            e.printStackTrace();
        }tableModel.fireTableDataChanged();

    }

    private static void updateDeleteTable() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, stock_quantity, reorder_level, price FROM products")) {
            DefaultTableModel deleteTableModel = (DefaultTableModel) deleteTable.getModel();
            deleteTableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                String name = rs.getString("name");
                int stock = rs.getInt("stock_quantity");
                int reorderLevel = rs.getInt("reorder_level");
                double price = rs.getDouble("price");
                deleteTableModel.addRow(new Object[]{name, stock, reorderLevel, price});
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while fetching products from database.");
            e.printStackTrace();
        }
    }

    private static void loadProductNames() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM products")) {
            productComboBox.removeAllItems();
            while (rs.next()) {
                productComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while fetching product names from database.");
            e.printStackTrace();
        }
    }
}


