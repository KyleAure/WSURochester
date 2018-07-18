
import Movie.*;
import User.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * User GUI for controlling User Interface operations.
 *
 * @author Kyle Jon Aure and Johnny Tran
 * @version 1.0
 */
public class UserGUI extends javax.swing.JFrame {

    //Create instance of PurpleBox to be used by GUI
    private PurpleBox userSession;

    //Other variables used to keep track
    private ArrayList<Movie> searchResults;
    private double totalCost;

    //TableModels used to display results to GUI
    private final DefaultTableModel searchTM;
    private final DefaultTableModel cartTM;
    private final DefaultTableModel checkedOutTM;

    /**
     * Constructor for GUI that accepts session and initializes variables.
     */
    public UserGUI() {
        //Initizalize other variables
        this.totalCost = 0.0;

        //Initalize components
        initComponents();

        //Initialize table models
        this.searchTM = (DefaultTableModel) searchTable.getModel();
        this.cartTM = (DefaultTableModel) cartTable.getModel();
        this.checkedOutTM = (DefaultTableModel) checkedOutTable.getModel();

    }

    public void setSession(PurpleBox session) {
        //Link to session started in Client's Main method
        this.userSession = session;

        //Remove test data button if we already have inventory items
        if (!userSession.search("All Movies").isEmpty()) {
            btTestData.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btSearch = new javax.swing.JButton();
        btCheckOut = new javax.swing.JButton();
        btClearCart = new javax.swing.JButton();
        btAddToCart = new javax.swing.JButton();
        btClearSearchList = new javax.swing.JButton();
        btRemoveItem = new javax.swing.JButton();
        btReturnAll = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        cbSearchAttribute = new javax.swing.JComboBox<>();
        sTitle = new javax.swing.JSeparator();
        sSearchOptions = new javax.swing.JSeparator();
        sSearchResults = new javax.swing.JSeparator();
        sShoppingCart = new javax.swing.JSeparator();
        tfPromoCode = new javax.swing.JTextField();
        tfTotalCost = new javax.swing.JTextField();
        tfSearch = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        searchTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        checkedOutTable = new javax.swing.JTable();
        lblPromoCode = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        lblPromoCodeStatus = new javax.swing.JLabel();
        lblSearchResults = new javax.swing.JLabel();
        lblSearchOptions = new javax.swing.JLabel();
        lblCart = new javax.swing.JLabel();
        lblOrders = new javax.swing.JLabel();
        btTestData = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Avenir", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Purple Box User");

        btSearch.setText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        btCheckOut.setText("CheckOut");
        btCheckOut.setEnabled(false);
        btCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCheckOutActionPerformed(evt);
            }
        });

        btClearCart.setText("Clear Cart");
        btClearCart.setEnabled(false);
        btClearCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearCartActionPerformed(evt);
            }
        });

        btAddToCart.setText("Add to Cart");
        btAddToCart.setEnabled(false);
        btAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddToCartActionPerformed(evt);
            }
        });

        btClearSearchList.setText("Clear Search");
        btClearSearchList.setEnabled(false);
        btClearSearchList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearSearchListActionPerformed(evt);
            }
        });

        btRemoveItem.setText("Remove Item");
        btRemoveItem.setEnabled(false);
        btRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveItemActionPerformed(evt);
            }
        });

        btReturnAll.setText("Return All");
        btReturnAll.setEnabled(false);
        btReturnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturnAllActionPerformed(evt);
            }
        });

        btExit.setText("Exit");
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        cbSearchAttribute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Movies", "Title", "Genre", "Blu-Ray", "DVD", "New Releases" }));
        cbSearchAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSearchAttributeActionPerformed(evt);
            }
        });

        tfPromoCode.setText("PromoCode");
        tfPromoCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPromoCodeKeyTyped(evt);
            }
        });

        tfTotalCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfTotalCost.setText(String.format("$%8.2f", totalCost));
        tfTotalCost.setToolTipText("");
        tfTotalCost.setEnabled(false);

        tfSearch.setText("Search");
        tfSearch.setEnabled(false);

        searchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Genre", "Release Date", "Type", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        searchTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        searchTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(searchTable);
        searchTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (searchTable.getColumnModel().getColumnCount() > 0) {
            searchTable.getColumnModel().getColumn(4).setResizable(false);
        }

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Genre", "Release Date", "Type", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(cartTable);

        checkedOutTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Genre", "Release Date", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        checkedOutTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(checkedOutTable);
        checkedOutTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lblPromoCode.setText("Type promotional code and hit enter to check:");
        lblPromoCode.setToolTipText("");

        lblTotalCost.setText("Total Cost: ");

        lblPromoCodeStatus.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblPromoCodeStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPromoCodeStatus.setText("Status");

        lblSearchResults.setFont(new java.awt.Font("Avenir", 0, 14)); // NOI18N
        lblSearchResults.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchResults.setText("Search Results");

        lblSearchOptions.setFont(new java.awt.Font("Avenir", 0, 14)); // NOI18N
        lblSearchOptions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchOptions.setText("Search Options");

        lblCart.setFont(new java.awt.Font("Avenir", 0, 14)); // NOI18N
        lblCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCart.setText("Shopping Cart");

        lblOrders.setFont(new java.awt.Font("Avenir", 0, 14)); // NOI18N
        lblOrders.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrders.setText("Orders");

        btTestData.setText("Test Data");
        btTestData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTestDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSearchResults, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSearchOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sTitle)
                    .addComponent(sSearchOptions)
                    .addComponent(sSearchResults)
                    .addComponent(sShoppingCart)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblTotalCost)
                                            .addComponent(lblPromoCode))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfPromoCode)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(10, 10, 10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(cbSearchAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfSearch))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btSearch)
                            .addComponent(btCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(btAddToCart)
                            .addComponent(btClearSearchList)
                            .addComponent(btRemoveItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btClearCart, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPromoCodeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(btReturnAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btTestData)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddToCart, btClearSearchList, btSearch});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCheckOut, btRemoveItem, lblPromoCodeStatus});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btTestData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSearchOptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSearchAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSearchOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSearchResults)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAddToCart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btClearSearchList))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(sSearchResults, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btRemoveItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btClearCart)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPromoCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPromoCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPromoCodeStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCost)
                    .addComponent(tfTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCheckOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sShoppingCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblOrders)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btReturnAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblPromoCode, tfPromoCode});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        String searchQuery = "";

        if (tfSearch.getText().compareTo("Search") != 0) { //ignore when default text is in tfSearch
            searchQuery = tfSearch.getText(); //Get additional query
        }

        searchResults = userSession.search( //Get search results from PurpleBox
                cbSearchAttribute.getSelectedItem().toString() + "#"
                + searchQuery);

        while (searchTM.getRowCount() > 0) { //clear any previous search results
            searchTM.removeRow(0);
        }

        for (Movie m : searchResults) {
            Object[] tempRow = m.toRow(0); //row format
            String tempCost = String.format("$%3.2f", userSession.getPrice(m.getType())); //get current price
            tempRow[4] = tempCost; //set current price

            searchTM.addRow(tempRow); //add row to table model
        }

        searchTable.setModel(searchTM); //display model on GUI

        //enable additional button features
        btAddToCart.setEnabled(true);
        btClearSearchList.setEnabled(true);

    }//GEN-LAST:event_btSearchActionPerformed

    private void cbSearchAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSearchAttributeActionPerformed
        // Only allow user to use the search text field only when title or genre is selected.
        if (cbSearchAttribute.getSelectedItem().toString().compareTo("Title") == 0
                || cbSearchAttribute.getSelectedItem().toString().compareTo("Genre") == 0) {
            tfSearch.setEnabled(true);
            tfSearch.setText("");
        }
        if (cbSearchAttribute.getSelectedItem().toString().compareTo("All Movies") == 0
                || cbSearchAttribute.getSelectedItem().toString().compareTo("Blu-Ray") == 0
                || cbSearchAttribute.getSelectedItem().toString().compareTo("DVD") == 0
                || cbSearchAttribute.getSelectedItem().toString().compareTo("New Releases") == 0) {
            tfSearch.setEnabled(false);
            tfSearch.setText("Search");
        }
    }//GEN-LAST:event_cbSearchAttributeActionPerformed

    private void btAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddToCartActionPerformed

        try { //try to add movie to cart first.  
            //Then if there is an exception the movie will not graphically movie.
            Movie temp = searchResults.get(searchTable.getSelectedRow()); //throws array out of bounds
            String tempCost = String.format("$%3.2f", userSession.getPrice(temp.getType())); //get current price

            Object[] tempRow = temp.toRow(1); //convert movie to row
            tempRow[4] = tempCost; //change cost

            userSession.addMovieToCart(temp); //throws exception if movie unabailable

            cartTM.addRow(tempRow); //add row
            cartTable.setModel(cartTM); //show model on GUI

            totalCost += userSession.getPrice(temp.getType());
            tfTotalCost.setText(String.format("$%3.2f", totalCost));
        } catch (MovieUnavailableException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Please select a movie first.\n" + e.toString());
        }

        btRemoveItem.setEnabled(true);
        btClearCart.setEnabled(true);
        btCheckOut.setEnabled(true);
    }//GEN-LAST:event_btAddToCartActionPerformed

    private void btClearSearchListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearSearchListActionPerformed

        searchResults.removeAll(searchResults); //remove all from search list.

        while (searchTM.getRowCount() > 0) { //clear any previous search results from model.
            searchTM.removeRow(0);
        }

        searchTable.setModel(searchTM); //show model on GUI

        btAddToCart.setEnabled(false);
        btClearSearchList.setEnabled(false);
    }//GEN-LAST:event_btClearSearchListActionPerformed

    private void btRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveItemActionPerformed
        try {
            ArrayList<Movie> tempList = userSession.getCart(); //EmptyCartException
            Movie tempMovie = tempList.get(cartTable.getSelectedRow()); //Get selected movie
            double tempCost = userSession.getPrice(tempMovie.getType()); //Get price

            userSession.removeMovieFromCart(tempMovie); //MovieUnavailableExeption

            cartTM.removeRow(cartTable.getSelectedRow()); //remove row from model
            cartTable.setModel(cartTM); //show model

            totalCost -= tempCost;
            tfTotalCost.setText(String.format("$%3.2f", totalCost));
        } catch (EmptyCartException | MovieUnavailableException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Please select a movie first.\n" + e.toString());
        }

        if (cartTM.getRowCount() == 0) {
            btRemoveItem.setEnabled(false);
            btClearCart.setEnabled(false);
        }

    }//GEN-LAST:event_btRemoveItemActionPerformed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExitActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btExitActionPerformed

    private void btClearCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearCartActionPerformed
        try {
            userSession.removeAllFromCart(); //throws exception

            while (cartTM.getRowCount() > 0) { //clear cart table modle
                cartTM.removeRow(0);
            }
            cartTable.setModel(cartTM);

            totalCost = 0.0;
            tfTotalCost.setText(String.format("$%3.2f", totalCost));

            btCheckOut.setEnabled(false);
            btRemoveItem.setEnabled(false);
            btClearCart.setEnabled(false);
        } catch (EmptyCartException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }//GEN-LAST:event_btClearCartActionPerformed

    private void btCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCheckOutActionPerformed
        try {
            ArrayList<Movie> tempCart = userSession.getCart();  //copy cart before checking out

            for (Movie m : tempCart) {
                checkedOutTM.addRow(m.toRow(2));
            }
            checkedOutTable.setModel(checkedOutTM);

            userSession.checkOutMovies(); //throws exception

            while (cartTM.getRowCount() > 0) { //clear cart table model
                cartTM.removeRow(0);
            }
            cartTable.setModel(cartTM);

            while (searchTM.getRowCount() > 0) { //clear search table model
                searchTM.removeRow(0);
            }
            searchTable.setModel(cartTM);

            totalCost = 0.0;
            tfTotalCost.setText(String.format("$%3.2f", totalCost));

            btCheckOut.setEnabled(false);
            btAddToCart.setEnabled(false);
            btClearSearchList.setEnabled(false);
            btReturnAll.setEnabled(true);
            btRemoveItem.setEnabled(false);
            btClearCart.setEnabled(false);
        } catch (EmptyCartException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btCheckOutActionPerformed

    private void btReturnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturnAllActionPerformed

        userSession.returnMovies();

        while (checkedOutTM.getRowCount() > 0) { //clear cart table modle
            checkedOutTM.removeRow(0);
        }
        checkedOutTable.setModel(checkedOutTM);

        btReturnAll.setEnabled(false);
    }//GEN-LAST:event_btReturnAllActionPerformed

    private void tfPromoCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPromoCodeKeyTyped
        try {
            userSession.applyPromoCode(tfPromoCode.getText());
            lblPromoCodeStatus.setText("Valid");
        } catch (InvalidPromoCodeException e) {
            lblPromoCodeStatus.setText("Invalid");
        }

    }//GEN-LAST:event_tfPromoCodeKeyTyped

    private void btTestDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTestDataActionPerformed
        BufferedReader br = null;
        File movieTestList = new File("src/MovieTestList.txt");
        ArrayList<Movie> temp = new ArrayList<>();

        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(movieTestList.getAbsoluteFile())));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sfl = line.split("#");
                Movie tempMovie = new Movie(sfl[0], //name
                        Boolean.valueOf(sfl[1]), //type
                        sfl[2], //genre
                        sfl[3], //release date
                        Boolean.valueOf(sfl[4]), //availability
                        Integer.parseInt(sfl[5]));      //quantity
                temp.add(tempMovie);
            }
        } catch (IOException ex) {
            System.out.println("Null Pointer");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Null Pointer");
            }
        }

        for (Movie m : temp) {
            userSession.addToInventory(m);
        }
        userSession.enableUnit();
        try {
            userSession.changePrice(true);
            userSession.changePrice(false);
        } catch (Exception e) {
            System.out.println("Useless exceptions were used.");
        }
    }//GEN-LAST:event_btTestDataActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UserGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddToCart;
    private javax.swing.JButton btCheckOut;
    private javax.swing.JButton btClearCart;
    private javax.swing.JButton btClearSearchList;
    private javax.swing.JButton btExit;
    private javax.swing.JButton btRemoveItem;
    private javax.swing.JButton btReturnAll;
    private javax.swing.JButton btSearch;
    private javax.swing.JButton btTestData;
    private javax.swing.JTable cartTable;
    private javax.swing.JComboBox<String> cbSearchAttribute;
    private javax.swing.JTable checkedOutTable;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblCart;
    private javax.swing.JLabel lblOrders;
    private javax.swing.JLabel lblPromoCode;
    private javax.swing.JLabel lblPromoCodeStatus;
    private javax.swing.JLabel lblSearchOptions;
    private javax.swing.JLabel lblSearchResults;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCost;
    private javax.swing.JSeparator sSearchOptions;
    private javax.swing.JSeparator sSearchResults;
    private javax.swing.JSeparator sShoppingCart;
    private javax.swing.JSeparator sTitle;
    private javax.swing.JTable searchTable;
    private javax.swing.JTextField tfPromoCode;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTotalCost;
    // End of variables declaration//GEN-END:variables
}
