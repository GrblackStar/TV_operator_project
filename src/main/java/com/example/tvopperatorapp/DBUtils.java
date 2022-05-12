package com.example.tvopperatorapp;
// the class contains only static methods
// cannot be instantiated
// handle everything with the database connection
// changing the scene

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.ArrayList;

public class DBUtils {
    public static void changeSceneToMain(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void changeSceneProvider (ActionEvent event,String fxmlFile, String title, String name){
        Parent root = null;

        if (name != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                ProviderMenuController providerMenuController = loader.getController();
                ChangeProviderInfoController changeProviderInfoController = loader.getController();
                DeleteQuestionProviderController deleteQuestionProviderController = loader.getController();

                providerMenuController.setProviderName(name);
                changeProviderInfoController.setProviderName(name);
                deleteQuestionProviderController.setProviderName(name);

            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void changeScenePackage (ActionEvent event, String fxmlFile, String title, String packageName){
        Parent root = null;

        if (packageName != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                AddChannelsToPackageController addChannelsToPackageController = loader.getController();
                addChannelsToPackageController.setPackageName(packageName);

            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void checkIfPackageExists (ActionEvent event, String packageName){
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM packages WHERE name = ?");
            psCheckUserExists.setString(1, packageName);
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(resultSet.isBeforeFirst()){
                System.out.println("ADDING TO EXISTING PACKAGE");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("ADDING TO EXISTING PACKAGE");
                alert.show();
            }else{
                System.out.println("CREATING NEW PACKAGE");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("CREATING NEW PACKAGE");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // fxmlFile -> the file that we want to go to
    // title -> the title of the scene
    //from the client menus scene to the MAIN scene:
    public static void changeSceneClient(ActionEvent event, String fxmlFile, String title, String username) {
        // parent is a base class for all the notes that have children in the scene
        // parent is the scene that the user is going to see:
        Parent root = null;

        //the user will be switching from the main scene to register or sign in scene
        if (username != null){
            try{
                // creates the scene from the fxml document
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));

                // turn the loader into an object that can be displayed on the scene -> load()
                // passing the object that is returned to our root variable
                root = loader.load();

                // we need to get the controller that we want to pass the data to:
                // essentially everything that includes the signed in client:
                ClientMenuController clientMenuController = loader.getController();
                PayBillClientController payBillClientController = loader.getController();
                ViewPlansClientController viewPlansClientController = loader.getController();
                ViewAllBillsClientController viewAllBillsClientController = loader.getController();

                clientMenuController.setUserUsername(username);
                payBillClientController.setUserUsername(username);
                viewAllBillsClientController.setUserUsername(username);
                viewPlansClientController.setUserUsername(username);

            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //a stage can have multiple scenes
        // we are getting the source of the click
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


    public static void registerClient (ActionEvent event, String firstName, String lastName,
                                       String egn, String phone, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psInsertRole = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ? ");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, "client");
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO users (name, egn, contactPhone, username, password) VALUES (?, ?, ?, ?, ?)");
                psInsert.setString(1, (firstName + " " + lastName));
                psInsert.setString(2, egn);
                psInsert.setString(3, phone);
                psInsert.setString(4, username);
                psInsert.setString(5, password);
                psInsertRole = connection.prepareStatement("INSERT INTO user_roles (id, role) VALUES (?, ?)");
                psInsertRole.setString(1, egn);
                psInsertRole.setString(2, "client");
                // we are just updating the database, without returning it, so we don't use resultSet
                psInsert.executeUpdate();
                psInsertRole.executeUpdate();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsertRole != null){
                try {
                    psInsertRole.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void logInClient (ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT password FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "client");
            // resultSt because we are selecting
            resultSet = preparedStatement.executeQuery();

            //checking if the username exists:
            // the user is not in the database
            if(resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided Credentials are incorrect!");
                alert.show();
            } else {
                // now if something is returned -> the user does exist in the database
                // we need to compare their password to the one in the database
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeSceneClient(event, "client-menu.fxml", "WELCOME!", username);
                    } else {
                        //if the passwords do not match
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void changeSceneAdmin(ActionEvent event, String fxmlFile, String title,
                                        String username, String password, String authCode){
        Parent root = null;
        if (username != null && password != null && authCode != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


    public static void registerAdmin (ActionEvent event, String firstName, String lastName,
                                         String egn, String phone, String username, String password, String authCode){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psInsertRole = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ? ");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, "admin");
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO users (name, egn, contactPhone, username, password) VALUES (?, ?, ?, ?, ?)");
                psInsert.setString(1, (firstName + " " + lastName));
                psInsert.setString(2, egn);
                psInsert.setString(3, phone);
                psInsert.setString(4, username);
                psInsert.setString(5, password);
                psInsertRole = connection.prepareStatement("INSERT INTO user_roles (id, role, authenticationCode) VALUES (?, ?, ?)");
                psInsertRole.setString(1, egn);
                psInsertRole.setString(2, "admin");
                psInsertRole.setString(3, authCode);
                // we are just updating the database, without returning it, so we don't use resultSet
                psInsert.executeUpdate();
                psInsertRole.executeUpdate();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsertRole != null){
                try {
                    psInsertRole.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void logInAdmin (ActionEvent event, String username, String password, String authCode){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT password FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "admin");
            // resultSt because we are selecting
            resultSet = preparedStatement.executeQuery();

            //checking if the username exists:
            // the user is not in the database
            if(resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided Credentials are incorrect!");
                alert.show();
            } else {
                // now if something is returned -> the user does exist in the database
                // we need to compare their password and their authCode to the ones in the database
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedCode = resultSet.getString("authenticationCode");
                    if (retrievedPassword.equals(password) && retrievedCode.equals(authCode)) {
                        changeSceneAdmin(event, "admin-menu.fxml", "WELCOME!", username, password, authCode);
                    } else {
                        //if the passwords do not match
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void changeSceneAdminClient (ActionEvent event, String fxmlFile, String title, String egn){
        Parent root = null;
        if (egn != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                AdminClientMenuController adminClientMenuController = loader.getController();
                AddPaymentController addPaymentController = loader.getController();
                ChangeClientInfoController changeClientInfoController = loader.getController();
                DeleteQuestionClientController deleteQuestionClientController = loader.getController();
                ClientBillsAdminController clientBillsAdminController = loader.getController();

                adminClientMenuController.setUserEgn(egn);
                addPaymentController.setUserEgn(egn);
                changeClientInfoController.setUserEgn(egn);
                deleteQuestionClientController.setUserEgn(egn);
                clientBillsAdminController.setUserEgn(egn);

            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void registerClientAdmin (ActionEvent event, String firstName, String lastName, String egn,
                                            String address, String phone, String packageName, String years){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psInsertContract = null;
        PreparedStatement psProviderId = null;
        ResultSet resultSet = null;

        String packageId;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN client_contract ON client_contract.clientId = users.egn WHERE users.egn = ? AND client_contract.address = ?");
            psCheckUserExists.setString(1, egn);
            psCheckUserExists.setString(2, address);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exists!");
                alert.show();
            }else{
                psProviderId = connection.prepareStatement("SELECT id FROM packages WHERE name = ?");
                psProviderId.setString(1, packageName);
                resultSet = psProviderId.executeQuery();
                packageId = resultSet.getString("id");

                psInsert = connection.prepareStatement("INSERT INTO users (name, egn, contactPhone) VALUES (?, ?, ?)");
                psInsert.setString(1, (firstName + " " + lastName));
                psInsert.setString(2, egn);
                psInsert.setString(3, phone);

                psInsertContract = connection.prepareStatement("INSERT INTO client_contract (ClientId, address, package, dealDate, expirationDate) VALUES (?, ?, ?, NOW(), (NOW()+INTERVAL ? YEAR))");
                psInsertContract.setString(1, egn);
                psInsertContract.setString(2, address);
                psInsertContract.setString(3, packageId);
                psInsertContract.setString(4, years);
                // we are just updating the database, without returning it, so we don't use resultSet
                psInsert.executeUpdate();
                psInsertContract.executeUpdate();

                psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN client_contract ON client_contract.clientId = users.egn WHERE users.egn = ? AND client_contract.address = ?");
                psCheckUserExists.setString(1, egn);
                psCheckUserExists.setString(2, address);
                resultSet = psCheckUserExists.executeQuery();

                if(resultSet.isBeforeFirst()){
                    System.out.println("User registered SUCCESSFULLY!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("User registered SUCCESSFULLY!");
                    alert.show();
                }else{
                    System.out.println("User NOT added to database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User NOT added to database");
                    alert.show();
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsertContract != null){
                try {
                    psInsertContract.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psProviderId != null){
                try {
                    psProviderId.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void addProvider (ActionEvent event, String providerName, String providerId, String dealDate,
                                    String endDate, String channelName, String category){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psInsertContract = null;
        PreparedStatement psChannels = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM providers WHERE id = ?");
            psCheckUserExists.setString(1, providerId);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Provider already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provider already exists!");
                alert.show();
            }else{

                psInsert = connection.prepareStatement("INSERT INTO providers (id, name) VALUES (?, ?)");
                psInsert.setString(1, providerId);
                psInsert.setString(2, providerName);

                psInsertContract = connection.prepareStatement("INSERT INTO provider_contract (providerId, dealDate, expirationDate) VALUES (?, ?, ?)");
                psInsertContract.setString(1, providerId);
                psInsertContract.setString(2, dealDate);
                psInsertContract.setString(3, endDate);

                psChannels = connection.prepareStatement("INSERT INTO channels (name, category, providerId) VALUES (?, ?, ?)");
                psChannels.setString(1, channelName);
                psChannels.setString(2, category);
                psChannels.setString(3, providerId);

                // we are just updating the database, without returning it, so we don't use resultSet
                psInsert.executeUpdate();
                psInsertContract.executeUpdate();
                psChannels.executeUpdate();

                psCheckUserExists = connection.prepareStatement("SELECT providers.id, providers.name, provider_contract.providerId, provider_contract.dealDate, provider_contract.expirationDate, channels.name, channels.category, channels.providerId FROM providers JOIN provider_contract ON providers.id=provider_contract.providerId JOIN channels ON channels.providerId=providers.id WHERE providers.id = ?");
                psCheckUserExists.setString(1, providerId);
                resultSet = psCheckUserExists.executeQuery();

                if(resultSet.isBeforeFirst()){
                    System.out.println("Provider registered SUCCESSFULLY!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Provider registered SUCCESSFULLY!");
                    alert.show();
                }else{
                    System.out.println("Provider NOT added to database");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provider NOT added to database");
                    alert.show();
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsertContract != null){
                try {
                    psInsertContract.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psChannels != null){
                try {
                    psChannels.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void regStatusClient (String username){
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ? ");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, "client");
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(resultSet.isBeforeFirst()){
                System.out.println("Registration of client SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Registration of client SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Registration of client UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Registration of client UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void regStatusAdmin (String username){
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN user_roles ON user_roles.id=users.egn WHERE username = ? AND role = ? ");
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, "admin");
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(resultSet.isBeforeFirst()){
                System.out.println("Registration of admin SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Registration of admin SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Registration of admin UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Registration of admin UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void viewClient (ActionEvent event, String egn){
        //checks if client exists
        // if exists, saves the egn and changes the scene to the menu
        // when we click ok button we check if they exist
        // if not, we get an alert

        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users JOIN user_roles ON user_roles.id=users.egn WHERE egn = ? AND role = ? ");
            psCheckUserExists.setString(1, egn);
            psCheckUserExists.setString(2, "client");
            resultSet = psCheckUserExists.executeQuery();

            // isBeforeFirst will return false if the resultSet is empty
            // if it returns true, that means that the username is already taken
            if(!resultSet.isBeforeFirst()){
                System.out.println("Client does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Client does not exist!");
                alert.show();
            }else{
                DBUtils.changeSceneAdminClient(event, "admin-client-menu", "CLIENT MENU", egn);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static ArrayList<String> choosePaymentClient (String username){

        ArrayList<String> unpayedBills = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT dateInvoice FROM bills JOIN users ON users.egn = bills.clientId WHERE users.username = ? AND paymentStatus = 'not payed' ");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                unpayedBills.add(resultSet.getString("dateInvoice"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return unpayedBills;

    }

    public static ArrayList<String> choosePaymentAdmin (String egn){

        ArrayList<String> unpayedBills = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT dateInvoice FROM bills JOIN users ON users.egn = bills.clientId WHERE users.egn = ? AND paymentStatus = 'not payed' ");
            preparedStatement.setString(1, egn);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                unpayedBills.add(resultSet.getString("dateInvoice"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return unpayedBills;
    }


    public static ArrayList<String> chooseClientPlan() {
        ArrayList<String> plans = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT name FROM packages");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                plans.add(resultSet.getString("name"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return plans;
    }



    public static void makePayment (ActionEvent event, String username, String date){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("UPDATE bills SET dateOfPayment = now(), paymentStatus = 'payed' FROM bills JOIN users ON bills.clientId = users.egn WHERE users.username = ? AND dateInvoice = ? ");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, date);
            preparedStatement.executeUpdate();

            check = connection.prepareStatement("SELECT dateInvoice FROM bills JOIN users ON users.egn = bills.clientId WHERE users.username = ? AND paymentStatus = 'not payed'");
            check.setString(1, username);
            resultSet = check.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Payment SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Payment SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Payment UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Payment UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void makePaymentAdmin (ActionEvent event, String egn, String date){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("UPDATE bills SET dateOfPayment = now(), paymentStatus = 'payed' FROM bills JOIN users ON bills.clientId = users.egn WHERE users.egn = ? AND dateInvoice = ? ");
            preparedStatement.setString(1, egn);
            preparedStatement.setString(2, date);
            preparedStatement.executeUpdate();

            check = connection.prepareStatement("SELECT dateInvoice FROM bills JOIN users ON users.egn = bills.clientId WHERE users.egn = ? AND paymentStatus = 'not payed'");
            check.setString(1, egn);
            resultSet = check.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Payment SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Payment SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Payment UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Payment UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void deleteClient(ActionEvent event, String egn){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("DELETE users, user_roles, client_contract, bills  FROM users  JOIN user_roles ON users.egn=user_roles.id  JOIN client_contract ON users.egn= client_contract.clientId  JOIN bills ON users.egn=bills.clientId WHERE users.egn = ? AND user_roles.role = ?");
            preparedStatement.setString(1, egn);
            preparedStatement.setString(2, "client");
            preparedStatement.executeUpdate();

            check = connection.prepareStatement("SELECT * FROM users WHERE egn = ?");
            check.setString(1, egn);
            resultSet = check.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Deleting SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Deleting SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Deleting UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Deleting UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteProvider(ActionEvent event, String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("DELETE providers, provider_contract, channels, channels_packages FROM providers JOIN provider_contract ON providers.id=provider_contract.providerId JOIN channels ON providers.id=channels.providerId JOIN channels_packages ON channels.id=channels_packages.channel WHERE provider.name=  ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            check = connection.prepareStatement("SELECT * FROM providers WHERE name = ?");
            check.setString(1, name);
            resultSet = check.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Deleting SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Deleting SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Deleting UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Deleting UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void changePrice (ActionEvent event, String name, String  price){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement check = null;
        PreparedStatement firstCheck = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");

            firstCheck = connection.prepareStatement("SELECT * FROM channels WHERE name = ?");
            firstCheck.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()){
                preparedStatement = connection.prepareStatement("UPDATE channels SET price = ? WHERE name = ?");
                preparedStatement.setString(1, price);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            } else{
                System.out.println("No such channel found!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No such channel found!");
                alert.show();
            }

            check = connection.prepareStatement("SELECT * FROM channels WHERE name = ? AND price = ?");
            check.setString(1, name);
            check.setString(2, price);
            resultSet = check.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Change of price SUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Change of price SUCCESSFUL");
                alert.show();
            }else{
                System.out.println("Change of price UNSUCCESSFUL");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Change of price UNSUCCESSFUL");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(firstCheck != null){
                try {
                    firstCheck.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void checkProvider(ActionEvent event, String fxmlFile, String title, String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT * FROM providers WHERE name = ? ");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                System.out.println("Provider not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provider not found in the database!");
                alert.show();
            } else {
                DBUtils.changeSceneProvider(event, fxmlFile, title, name);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void checkIfChannelExists (ActionEvent event, String packageName, String channelName){
        Connection connection = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");

            check = connection.prepareStatement("SELECT * FROM channels WHERE channels.name = ?");
            check.setString(1, channelName);
            resultSet = check.executeQuery();

            if(resultSet.isBeforeFirst()){
                DBUtils.addChannel(event, packageName, channelName);
            }else{
                System.out.println("Channel does not exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Channel does not exist");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void addChannel (ActionEvent event, String packageName, String channelName){
        Connection connection = null;
        PreparedStatement preparedInsert = null;
        PreparedStatement preparedUpdate = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");

            preparedInsert = connection.prepareStatement("INSERT INTO channels_packages(channel, package) SELECT channels.id, packages.id FROM channels JOIN channels_packages ON channels.id = channels_packages.channel JOIN packages ON packages.id=channels_packages.package WHERE channels.name = ? AND packages.name = ?");
            preparedInsert.setString(1, channelName);
            preparedInsert.setString(2, packageName);
            preparedInsert.executeUpdate();

            preparedUpdate = connection.prepareStatement("UPDATE package SET package.price = sum(channel.price) FROM  channels JOIN channels_packages ON channels.id = channels_packages.channel JOIN packages ON packages.id=channels_packages.package WHERE packages.name = ?");
            preparedUpdate.setString(1, packageName);
            preparedUpdate.executeUpdate();

            check = connection.prepareStatement("SELECT * FROM channels_packages JOIN channels ON channels.id = channels_packages.channel JOIN packages ON packages.id=channels_packages.package WHERE packages.name = ?");
            check.setString(1, packageName);
            resultSet = check.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Channel added SUCCESSFULLY");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Channel added SUCCESSFULLY");
                alert.show();
            }else{
                System.out.println("Channel NOT added");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Channel NOT added");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedInsert != null){
                try {
                    preparedInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedUpdate != null){
                try {
                    preparedUpdate.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check != null){
                try {
                    check.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // static methods for fetching the records from database
    // ObservableList -> A list that allows listeners to track changes when they occur.

    // method for the yourPlans table:
    public static ObservableList<TableYourPlan> getAllTableYourPlan(String username) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");

            preparedStatement = connection.prepareStatement("SELECT packages.name, packages.price, channels.name FROM channels JOIN channels_packages ON channels.id = channels_packages.channel JOIN packages ON packages.id=channels_packages.package JOIN client_contract ON packages.id=client_contract.package JOIN users ON client_contract.clientId=users.egn WHERE users.username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            ObservableList<TableYourPlan> list = getYourPlanObjects(resultSet);
            return list;

        } catch (SQLException | ClassNotFoundException s){
            s.printStackTrace();
            throw s;
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<TableYourPlan> getYourPlanObjects(ResultSet resultSet) throws ClassNotFoundException, SQLException{

        ObservableList<TableYourPlan> list = FXCollections.observableArrayList();
        try {
            while (resultSet.next()){
                TableYourPlan tableYourPlan = new TableYourPlan();
                tableYourPlan.setPlanName(resultSet.getString("name"));
                tableYourPlan.setPriceProperty(resultSet.getDouble("price"));
                tableYourPlan.setChannelsName(resultSet.getString("name"));

                list.add(tableYourPlan);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



    // fot allPlans:
    public static ObservableList<TableAllPlans> getAllTableAllPlans() throws ClassNotFoundException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement(" SELECT packages.name, packages.price, channels.name FROM channels JOIN channels_packages ON channels.id = channels_packages.channel JOIN packages ON packages.id=channels_packages.package ORDER BY channels_packages.package");
            resultSet = preparedStatement.executeQuery();

            ObservableList<TableAllPlans> list = getAllPlansObjects(resultSet);
            return list;

        } catch (SQLException | ClassNotFoundException s){
            s.printStackTrace();
            throw s;
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<TableAllPlans> getAllPlansObjects(ResultSet resultSet) throws ClassNotFoundException, SQLException{
        ObservableList<TableAllPlans> list = FXCollections.observableArrayList();
        try{
            while (resultSet.next()){
                TableAllPlans tableAllPlans = new TableAllPlans();
                tableAllPlans.setPlanNameAll(resultSet.getString("name"));
                tableAllPlans.setPricePropertyAll(resultSet.getDouble("price"));
                tableAllPlans.setChannelsNameAll(resultSet.getString("name"));

                list.add(tableAllPlans);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }


    //ERROR line 1672
    // for yourBills:
    public static ObservableList<TableYourBills> getAllTableYourBills (String username)throws ClassNotFoundException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT bills.id, bills.dateInvoice, packages.price, bills.paymentStatus FROM bills JOIN users ON bills.clientId=users.egn JOIN client_contract ON users.egn=client_contract.clientId JOIN packages ON client_contract.package=packages.id WHERE users.username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            ObservableList<TableYourBills> list = getYourBillsObjects(resultSet);
            return list;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<TableYourBills> getYourBillsObjects(ResultSet resultSet)  throws ClassNotFoundException, SQLException{
        ObservableList<TableYourBills> list = FXCollections.observableArrayList();

        try {
            while(resultSet.next()){
                TableYourBills tableYourBills = new TableYourBills();
                tableYourBills.setNumber(resultSet.getInt("id"));
               // tableYourBills.setDate(resultSet.getDate("dateInvoice"));
                tableYourBills.setPrice(resultSet.getDouble("price"));
                tableYourBills.setStatus(resultSet.getString("paymentStatus"));

                list.add(tableYourBills);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    //ERROR line 1739, 1740
    //for client bills
    public static ObservableList<TableClientBills> getAllTableClientBills (String egn) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT bills.id, packages.price, bills.dateInvoice, bills.dateOfPayment, bills.paymentStatus FROM bills JOIN users ON bills.clientId=users.egn JOIN client_contract ON users.egn=client_contract.clientId JOIN packages ON client_contract.package=packages.id WHERE users.egn=?");
            preparedStatement.setString(1, egn);
            resultSet = preparedStatement.executeQuery();

            ObservableList<TableClientBills> list = getClientBillsObjects(resultSet);
            return list;

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            throw e;

        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<TableClientBills> getClientBillsObjects(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        ObservableList<TableClientBills> list = FXCollections.observableArrayList();

        try {
            while (resultSet.next()){
                TableClientBills tableClientBills = new TableClientBills();
                tableClientBills.setNumber(resultSet.getInt("id"));
                tableClientBills.setPrice(resultSet.getDouble("price"));
                //tableClientBills.setDateInvoice(resultSet.getDate("dateInvoice"));
                //tableClientBills.setDatePayment(resultSet.getDate("dateOfPayment"));
                tableClientBills.setStatus(resultSet.getString("paymentStatus"));

                list.add(tableClientBills);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }

    }



    //for Provider menu:
    public static ObservableList<TableProviderMenu> getAllTableProviderMenu (String name) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulsatcom", "root", "78spnblackstar19");
            preparedStatement = connection.prepareStatement("SELECT channels.name, channels.category FROM channels JOIN providers ON channels.providerId = providers.id WHERE providers.name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            ObservableList<TableProviderMenu> list = getProviderMenuObjects(resultSet);
            return list;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<TableProviderMenu> getProviderMenuObjects (ResultSet resultSet) throws ClassNotFoundException, SQLException{
        ObservableList<TableProviderMenu> list = FXCollections.observableArrayList();

        try {
            while (resultSet.next()){
                TableProviderMenu tableProviderMenu = new TableProviderMenu();
                tableProviderMenu.setChannelName(resultSet.getString("name"));
                tableProviderMenu.setChannelCategory(resultSet.getString("category"));

                list.add(tableProviderMenu);
            }
            return list;
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }











}
