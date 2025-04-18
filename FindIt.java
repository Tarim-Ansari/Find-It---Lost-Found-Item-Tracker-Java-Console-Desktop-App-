import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
public class FindItGUI {
 private static java.util.List<Item> items = new ArrayList<>();
 private static java.util.List<ClaimRequest> claimRequests = new ArrayList<>();
 private static Map<String, String> claimStatus = new HashMap<>(); // To store claim status
 private static JFrame frame;
 public static void main(String[] args) {
 SwingUtilities.invokeLater(FindItGUI::createAndShowGUI);
 }
 private static void createAndShowGUI() {
 frame = new JFrame("FindIt: A Lost and Found Software");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setSize(500, 600); // Increased height to accommodate the logo and title
 frame.setLayout(new BorderLayout(10, 10));
 frame.setLocationRelativeTo(null);

 JPanel mainPanel = new JPanel();
 mainPanel.setLayout(new BorderLayout(10, 10));
 mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
 mainPanel.setBackground(new Color(230, 230, 250));
 // Top panel for logo (left) and title (center)
 JPanel topPanel = new JPanel(new BorderLayout(10, 10));
 topPanel.setBackground(new Color(230, 230, 250));
 // Load and add the logo to the left
 try {
 BufferedImage logoImg = ImageIO.read(new
File("C:\\Users\\admin\\Desktop\\13\\findit_logo.png")); // Update this path
 Image scaledImg = logoImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH); //
Scale the image
 JLabel logoLabel = new JLabel(new ImageIcon(scaledImg));
 logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Add some right
padding
 topPanel.add(logoLabel, BorderLayout.WEST);
 } catch (IOException e) {
 System.err.println("Error loading logo: " + e.getMessage());
 }
 // Add the title centered
 JLabel titleLabel = new JLabel("FindIt: A Lost and Found Software",
SwingConstants.CENTER);
 titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
 titleLabel.setForeground(Color.BLUE);
 topPanel.add(titleLabel, BorderLayout.CENTER);
 // Buttons panel
 JPanel buttonsPanel = new JPanel();
 buttonsPanel.setLayout(new GridLayout(7, 1, 10, 10)); // 7 rows for buttons
 buttonsPanel.setBackground(new Color(230, 230, 250));
 JButton postButton = createStyledButton("Post Found Items", new Color(173, 216, 230));
 JButton claimButton = createStyledButton("Claim your Belongings", new Color(144, 238,
144));
 JButton viewClaimButton = createStyledButton("View Claim Requests", new Color(255,
218, 185));
 JButton trackClaimButton = createStyledButton("Track Claim Request", new Color(240,
230, 140));
 JButton allItemsButton = createStyledButton("View All Reported Items", new Color(221,
160, 221));
 JButton exitButton = createStyledButton("Exit", new Color(255, 182, 193));
 postButton.addActionListener(e -> showPostItemDialog());
 claimButton.addActionListener(e -> showClaimDialog());
 viewClaimButton.addActionListener(e -> showViewClaimDialog());
 trackClaimButton.addActionListener(e -> showTrackClaimDialog());
 allItemsButton.addActionListener(e -> showAllItemsDialog());
 exitButton.addActionListener(e -> {
 JOptionPane.showMessageDialog(frame, "Exiting... Thank you for using FindIt!");
 System.exit(0);
 });
 buttonsPanel.add(postButton);
 buttonsPanel.add(claimButton);
 buttonsPanel.add(viewClaimButton);
 buttonsPanel.add(trackClaimButton);
 buttonsPanel.add(allItemsButton);
 buttonsPanel.add(exitButton);
 mainPanel.add(topPanel, BorderLayout.NORTH);
 mainPanel.add(buttonsPanel, BorderLayout.CENTER);
 frame.add(mainPanel, BorderLayout.CENTER);
 frame.setVisible(true);
 }
 private static JButton createStyledButton(String text, Color color) {
 JButton button = new JButton(text);
 button.setFont(new Font("Arial", Font.BOLD, 16));
 button.setForeground(Color.BLACK);
 button.setBackground(color);
 button.setFocusPainted(false);
 button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
 return button;
 }
 private static void showPostItemDialog() {
 JDialog postDialog = new JDialog(frame, "Post Item", true);
 postDialog.setSize(400, 400);
 postDialog.setLayout(new GridLayout(8, 2, 5, 5));
 postDialog.setLocationRelativeTo(frame);
 postDialog.getContentPane().setBackground(new Color(255, 248, 220));

 JTextField nameField = new JTextField();
 JTextField descField = new JTextField();
 JTextField locationField = new JTextField();
 JTextField contactField = new JTextField();
 JComboBox<String> statusBox = new JComboBox<>(new String[]{"Lost", "Found"});
 JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Electronics",
"Documents", "Accessories", "Others"});
 JButton photoButton = createStyledButton("Upload Photo", new Color(255, 215, 0));
 JLabel photoLabel = new JLabel("No photo selected");
 JButton submitButton = createStyledButton("Submit", new Color(135, 206, 250));
 final String[] photoPath = {null};
 // Add KeyListener to move focus on Enter key press
 nameField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 descField.requestFocus();
 }
 }
 });
 descField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 locationField.requestFocus();
 }
 }
 });
 locationField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 contactField.requestFocus();
 }
 }
 });
 contactField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 statusBox.requestFocus();
 }
 }
 });
 statusBox.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 categoryBox.requestFocus();
 }
 }
 });
 categoryBox.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 photoButton.requestFocus();
 }
 }
 });
 photoButton.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 submitButton.requestFocus();
 }
 }
 });
 photoButton.addActionListener(e -> {
 JFileChooser fileChooser = new JFileChooser();
 FileNameExtensionFilter filter = new FileNameExtensionFilter(
 "Image files", "jpg", "jpeg", "png", "gif");
 fileChooser.setFileFilter(filter);
 int returnVal = fileChooser.showOpenDialog(postDialog);
 if (returnVal == JFileChooser.APPROVE_OPTION) {
 File file = fileChooser.getSelectedFile();
 photoPath[0] = file.getAbsolutePath();
 photoLabel.setText("Photo: " + file.getName());
 }
 });
 submitButton.addActionListener(e -> {
 String name = nameField.getText().trim();
 String description = descField.getText().trim();
 String location = locationField.getText().trim();
 String contact = contactField.getText().trim();
 String status = (String) statusBox.getSelectedItem();
 String category = (String) categoryBox.getSelectedItem();
 if (name.isEmpty() || description.isEmpty() || location.isEmpty() || contact.isEmpty())
{
 JOptionPane.showMessageDialog(postDialog, "All fields are required!", "Error",
JOptionPane.ERROR_MESSAGE);
 } else {
 items.add(new Item(name, description, status, category, location, photoPath[0],
contact));
 claimStatus.put(name, "Not Returned Yet"); // Initialize status
 JOptionPane.showMessageDialog(postDialog, "Item posted successfully!");
 postDialog.dispose();
 }
 });
 postDialog.add(new JLabel("Item Name:"));
 postDialog.add(nameField);
 postDialog.add(new JLabel("Description:"));
 postDialog.add(descField);
 postDialog.add(new JLabel("Status:"));
 postDialog.add(statusBox);
 postDialog.add(new JLabel("Category:"));
 postDialog.add(categoryBox);
 postDialog.add(new JLabel("Location:"));
 postDialog.add(locationField);
 postDialog.add(new JLabel("To Claim Contact:"));
 postDialog.add(contactField);
 postDialog.add(new JLabel("Photo:"));
 postDialog.add(photoButton);
 postDialog.add(photoLabel);
 postDialog.add(submitButton);
 postDialog.setVisible(true);
 }
 private static void showClaimDialog() {
 JDialog claimDialog = new JDialog(frame, "Claim your Belongings", true);
 claimDialog.setSize(600, 600);
 claimDialog.setLayout(new BorderLayout(5, 5));
 claimDialog.setLocationRelativeTo(frame);
 claimDialog.getContentPane().setBackground(new Color(224, 255, 255));
 JPanel claimPanel = new JPanel(new FlowLayout());
 claimPanel.setBackground(new Color(240, 255, 240));
 JTextField belongingsField = new JTextField(15);
 JComboBox<String> categoryBox = new JComboBox<>(new String[]{"All", "Electronics",
"Documents", "Accessories", "Others"});

 claimPanel.add(new JLabel("Enter your Belongings name:"));
 claimPanel.add(belongingsField);
 claimPanel.add(new JLabel("Category:"));
 claimPanel.add(categoryBox);
 JPanel resultsPanel = new JPanel(new BorderLayout());
 JTextArea resultsArea = new JTextArea(10, 40);
 resultsArea.setEditable(false);
 resultsArea.setFont(new Font("Arial", Font.PLAIN, 14));
 JScrollPane scrollPane = new JScrollPane(resultsArea);

 JPanel photoPanel = new JPanel(new BorderLayout());
 JLabel photoLabel = new JLabel("No photo available", SwingConstants.CENTER);
 photoPanel.add(photoLabel, BorderLayout.CENTER);
 JPanel claimRequestPanel = new JPanel(new GridLayout(4, 2, 5, 5));
 JTextField claimDescField = new JTextField();
 JButton invoiceButton = createStyledButton("Upload Invoice", new Color(255, 215, 0));
 JLabel invoiceLabel = new JLabel("No invoice selected");
 JButton submitClaimButton = createStyledButton("Submit Claim", new Color(135, 206,
250));

 final String[] invoicePath = {null};
 final Item[] selectedItem = {null};
 invoiceButton.addActionListener(e -> {
 JFileChooser fileChooser = new JFileChooser();
 FileNameExtensionFilter filter = new FileNameExtensionFilter(
 "Image files", "jpg", "jpeg", "png", "gif");
 fileChooser.setFileFilter(filter);
 int returnVal = fileChooser.showOpenDialog(claimDialog);
 if (returnVal == JFileChooser.APPROVE_OPTION) {
 File file = fileChooser.getSelectedFile();
 invoicePath[0] = file.getAbsolutePath();
 invoiceLabel.setText("Invoice: " + file.getName());
 }
 });
 belongingsField.addActionListener(e -> {
 String belongingsName = belongingsField.getText().trim().toLowerCase();
 String selectedCategory = (String) categoryBox.getSelectedItem();
 resultsArea.setText("Matching Results:\n");
 photoLabel.setIcon(null);
 photoLabel.setText("No photo available");
 selectedItem[0] = null;
 for (Item item : items) {
 if ((belongingsName.isEmpty() ||
item.getName().toLowerCase().contains(belongingsName)) &&
 (selectedCategory.equals("All") || item.getCategory().equals(selectedCategory))) {
 resultsArea.append(item.toString() + "\n");
 selectedItem[0] = item;
 if (item.getPhotoPath() != null) {
 try {
 BufferedImage img = ImageIO.read(new File(item.getPhotoPath()));
 Image scaledImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
 photoLabel.setIcon(new ImageIcon(scaledImg));
 photoLabel.setText("");
 } catch (Exception ex) {
 photoLabel.setText("Error loading photo");
 }
 }
 break;
 }
 }
 });
 submitClaimButton.addActionListener(e -> {
 if (selectedItem[0] == null) {
 JOptionPane.showMessageDialog(claimDialog, "Please select an item first!",
 "Error", JOptionPane.ERROR_MESSAGE);
 return;
 }
 String claimDescription = claimDescField.getText().trim();
 if (claimDescription.isEmpty() || invoicePath[0] == null) {
 JOptionPane.showMessageDialog(claimDialog, "Claim description and invoice are
required!",
 "Error", JOptionPane.ERROR_MESSAGE);
 } else {
 claimRequests.add(new ClaimRequest(selectedItem[0], claimDescription,
invoicePath[0]));
 JOptionPane.showMessageDialog(claimDialog, "Claim request submitted
successfully!");
 claimDescField.setText("");
 invoicePath[0] = null;
 invoiceLabel.setText("No invoice selected");
 }
 });
 claimRequestPanel.add(new JLabel("Claim Description:"));
 claimRequestPanel.add(claimDescField);
 claimRequestPanel.add(new JLabel("Invoice:"));
 claimRequestPanel.add(invoiceButton);
 claimRequestPanel.add(new JLabel(""));
 claimRequestPanel.add(invoiceLabel);
 claimRequestPanel.add(new JLabel(""));
 claimRequestPanel.add(submitClaimButton);
 resultsPanel.add(scrollPane, BorderLayout.CENTER);
 resultsPanel.add(photoPanel, BorderLayout.SOUTH);
 claimDialog.add(claimPanel, BorderLayout.NORTH);
 claimDialog.add(resultsPanel, BorderLayout.CENTER);
 claimDialog.add(claimRequestPanel, BorderLayout.SOUTH);
 claimDialog.setVisible(true);
 }
 private static void showViewClaimDialog() {
 JDialog viewClaimDialog = new JDialog(frame, "View Claim Requests", true);
 viewClaimDialog.setSize(600, 500);
 viewClaimDialog.setLayout(new BorderLayout(5, 5));
 viewClaimDialog.setLocationRelativeTo(frame);
 viewClaimDialog.getContentPane().setBackground(new Color(224, 255, 255));
 JTextArea claimsArea = new JTextArea();
 claimsArea.setEditable(false);
 claimsArea.setFont(new Font("Arial", Font.PLAIN, 14));
 JScrollPane scrollPane = new JScrollPane(claimsArea);
 JPanel invoicePanel = new JPanel();
 JLabel invoiceLabel = new JLabel("Select a claim to view invoice");
 invoicePanel.add(invoiceLabel);
 claimsArea.addMouseListener(new MouseAdapter() {
 @Override
 public void mouseClicked(MouseEvent e) {
 String selectedText = claimsArea.getSelectedText();
 if (selectedText != null && !selectedText.isEmpty()) {
 for (ClaimRequest claim : claimRequests) {
 if (selectedText.contains(claim.getItem().getName())) {
 if (claim.getInvoicePath() != null) {
 try {
 BufferedImage img = ImageIO.read(new File(claim.getInvoicePath()));
 Image scaledImg = img.getScaledInstance(200, 150,
Image.SCALE_SMOOTH);
 invoiceLabel.setIcon(new ImageIcon(scaledImg));
 invoiceLabel.setText("");
 } catch (Exception ex) {
 invoiceLabel.setIcon(null);
 invoiceLabel.setText("Error loading invoice");
 }
 } else {
 invoiceLabel.setIcon(null);
 invoiceLabel.setText("No invoice available");
 }
 break;
 }
 }
 }
 }
 });
 claimsArea.setText("All Claim Requests:\n");
 if (claimRequests.isEmpty()) {
 claimsArea.append("No claim requests yet.");
 } else {
 for (ClaimRequest claim : claimRequests) {
 claimsArea.append(claim.toString() + "\n");
 }
 }
 viewClaimDialog.add(scrollPane, BorderLayout.CENTER);
 viewClaimDialog.add(invoicePanel, BorderLayout.SOUTH);
 viewClaimDialog.setVisible(true);
 }
 private static void showAllItemsDialog() {
 JDialog allItemsDialog = new JDialog(frame, "All Reported Items", true);
 allItemsDialog.setSize(600, 400);
 allItemsDialog.setLayout(new BorderLayout(5, 5));
 allItemsDialog.setLocationRelativeTo(frame);
 allItemsDialog.getContentPane().setBackground(new Color(224, 255, 255));
 JTextArea itemsArea = new JTextArea();
 itemsArea.setEditable(false);
 itemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
 JScrollPane scrollPane = new JScrollPane(itemsArea);
 Set<String> claimedItemNames = new HashSet<>();
 for (ClaimRequest claim : claimRequests) {
 claimedItemNames.add(claim.getItem().getName());
 }
 StringBuilder claimedItems = new StringBuilder("Claimed Items:\n");
 StringBuilder unclaimedItems = new StringBuilder("Unclaimed Items:\n");
 if (items.isEmpty()) {
 claimedItems.append("No claimed items yet.\n");
 unclaimedItems.append("No unclaimed items yet.\n");
 } else {
 boolean hasClaimed = false;
 boolean hasUnclaimed = false;
 for (Item item : items) {
 if (claimedItemNames.contains(item.getName())) {
 claimedItems.append(item.getName()).append("\n");
 hasClaimed = true;
 } else {
 unclaimedItems.append(item.getName()).append("\n");
 hasUnclaimed = true;
 }
 }
 if (!hasClaimed) claimedItems.append("No claimed items yet.\n");
 if (!hasUnclaimed) unclaimedItems.append("No unclaimed items yet.\n");
 }
 itemsArea.setText(claimedItems.toString() + "\n" + unclaimedItems.toString());
 allItemsDialog.add(scrollPane, BorderLayout.CENTER);
 allItemsDialog.setVisible(true);
 }
 private static void showTrackClaimDialog() {
 JDialog trackClaimDialog = new JDialog(frame, "Track Claim Request", true);
 trackClaimDialog.setSize(600, 400);
 trackClaimDialog.setLocationRelativeTo(frame);
 trackClaimDialog.getContentPane().setBackground(new Color(224, 255, 255));
 JPanel trackPanel = new JPanel();
 trackPanel.setLayout(new GridLayout(items.size() + 1, 2, 5, 5)); // +1 for submit button
 trackPanel.setBackground(new Color(224, 255, 255));
 Map<String, JComboBox<String>> statusBoxes = new HashMap<>();
 if (items.isEmpty()) {
 trackPanel.add(new JLabel("No items reported yet."));
 trackPanel.add(new JLabel(""));
 } else {
 for (Item item : items) {
 JLabel itemLabel = new JLabel(item.getName());
 JComboBox<String> statusBox = new JComboBox<>(new String[]{"Returned", "Not
Returned Yet"});
 statusBox.setSelectedItem(claimStatus.getOrDefault(item.getName(), "Not Returned
Yet"));
 trackPanel.add(itemLabel);
 trackPanel.add(statusBox);
 statusBoxes.put(item.getName(), statusBox);
 }
 }
 JButton submitButton = createStyledButton("Submit Status", new Color(135, 206, 250));
 submitButton.addActionListener(e -> {
 for (Item item : items) {
 String itemName = item.getName();
 JComboBox<String> statusBox = statusBoxes.get(itemName);
 if (statusBox != null) {
 claimStatus.put(itemName, (String) statusBox.getSelectedItem());
 }
 }
 JOptionPane.showMessageDialog(trackClaimDialog, "Claim statuses updated
successfully!");
 trackClaimDialog.dispose();
 });
 trackPanel.add(new JLabel(""));
 trackPanel.add(submitButton);
 JScrollPane scrollPane = new JScrollPane(trackPanel);
 trackClaimDialog.add(scrollPane, BorderLayout.CENTER);
 trackClaimDialog.setVisible(true);
 }
}
class Item {
 private String name;
 private String description;
 private String status;
 private String category;
 private String location;
 private String photoPath;
 private String contact;
 public Item(String name, String description, String status, String category,
 String location, String photoPath, String contact) {
 this.name = name;
 this.description = description;
 this.status = status;
 this.category = category;
 this.location = location;
 this.photoPath = photoPath;
 this.contact = contact;
 }
 public String getName() {
 return name;
 }
 public String getCategory() {
 return category;
 }
 public String getPhotoPath() {
 return photoPath;
 }
 @Override
 public String toString() {
 String photoInfo = (photoPath != null) ? " | Photo: Yes" : " | Photo: No";
 return "Item: " + name + " | Status: " + status + " | Category: " + category +
 " | Location: " + location + " | Contact: " + contact + photoInfo + " | " + description;
 }
}
class ClaimRequest {
 private Item item;
 private String description;
 private String invoicePath;
 public ClaimRequest(Item item, String description, String invoicePath) {
 this.item = item;
 this.description = description;
 this.invoicePath = invoicePath;
 }
 public Item getItem() {
 return item;
 }
 public String getInvoicePath() {
 return invoicePath;
 }
 @Override
 public String toString() {
 return "Claim for: " + item.getName() + " | Description: " + description +
 " | Invoice: " + (invoicePath != null ? "Yes" : "No");
 }
}
