package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;


//control coupling, stamp coupling
/**
 * This class controls the flow of place order usecase in our AIMS project
 */

 // Vi phạm Single responsibility princible do lớp đang thực hiện cả chức năng 
 // tính phí vận chuyển (method calculateShippingFee)
 // kiểm tra thông tin đơn hàng (method validateDeliveryInfo)
 // Cần tách các chức năng này ra 1 lớp riêng
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{ //Functional cohesion
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{ //Sequential Cohesion
        //createOrder và createInvoice: Cả hai phương thức này đều liên quan đến việc tạo đơn hàng và hóa đơn theo một thứ tự cụ thể.
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    } //Sequential Cohesion

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{ //Temporal Cohesion
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        //
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */

    //Procedural cohesion
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    
    public boolean validatePhoneNumber(String phoneNumber) { //Procedural Cohesion
        //
    	// TODO: your work
    	return false;
    }
    
    public boolean validateName(String name) { //Procedural Cohesion
    	// TODO: your work
    	return false;
    }
    
    public boolean validateAddress(String address) { //Procedural Cohesion
    	// TODO: your work
    	return false;
    }
    

    // procedural cohesion, this method can be another component with fast delivery option 
    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){ // Logical Cohesion
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
