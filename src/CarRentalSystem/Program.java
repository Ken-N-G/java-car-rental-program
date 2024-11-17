package CarRentalSystem;

// Class to generate pages and run read/write operations || anything related to program flow and initialization goes here

public class Program {
    public static LoginPage loginPage;
    public static RegisterPage registerPage;
    public static CustomerHomepage customerHomepage;
    public static CustomerAccountManagementPage customerAccountManagementPage;
    public static CustomerFeedbackPage customerFeedbackPage;
    public static CustomerBookingHomepage customerBookingHomepage;
    public static CustomerRentalsPage customerRentalsPage;
    public static CustomerCreateBookingPage customerCreateBookingPage;
    public static CustomerViewManageBookingPage customerViewBookingPage;
    public static CustomerPaymentHomepage customerPaymentHomepage;
    public static CustomerMakePaymentPage customerMakePaymentPage;
    public static CustomerViewReceiptPage customerViewReceiptPage;
    public static AdminHomepage adminHomepage;
    public static AdminBookingRequestedPage adminBookingRequestedPage;
    public static AdminRentalPage adminRentalPage;
    public static AdminAddRentalPage adminAddRentalPage;
    public static AdminUpdateRentalPage adminUpdateRentalPage;
    public static AdminReportPage adminReportPage;
    public static AdminAccountManagementPage adminAccountManagementPage;
    public static AdminAddAdminPage adminAddAdminPage;
    public static AdminUpdateAdminPage adminUpdateAdminPage;
    public static AdminRegisterationRequestedPage adminRegisterationRequestedPage;
    public static AdminViewCustomerPage adminViewCustomerPage;
    public static Customer loggedCustomer = null;
    public static Admin loggedAdmin = null;
    
    public Program(){
        Utility.boot();
        Utility.readFromFile();
        Utility.updateBookingStatusByDate();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        customerHomepage = new CustomerHomepage();
        customerAccountManagementPage = new CustomerAccountManagementPage();
        customerFeedbackPage = new CustomerFeedbackPage();
        customerBookingHomepage = new CustomerBookingHomepage();
        customerRentalsPage = new CustomerRentalsPage();
        customerCreateBookingPage = new CustomerCreateBookingPage();
        customerViewBookingPage = new CustomerViewManageBookingPage();
        customerPaymentHomepage = new CustomerPaymentHomepage();
        customerMakePaymentPage = new CustomerMakePaymentPage();
        customerViewReceiptPage = new CustomerViewReceiptPage();
        
        adminHomepage = new AdminHomepage();
        adminBookingRequestedPage = new AdminBookingRequestedPage();
        adminRentalPage = new AdminRentalPage();
        adminAddRentalPage = new AdminAddRentalPage();
        adminUpdateRentalPage = new AdminUpdateRentalPage();
        adminReportPage = new AdminReportPage();
        adminAccountManagementPage = new AdminAccountManagementPage();
        adminAddAdminPage = new AdminAddAdminPage();
        adminUpdateAdminPage = new AdminUpdateAdminPage();
        adminRegisterationRequestedPage = new AdminRegisterationRequestedPage();
        adminViewCustomerPage = new AdminViewCustomerPage();
        
    }
    
}
