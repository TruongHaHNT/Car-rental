/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.cart;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import truonghn.tbl_Car.Tbl_CarDTO;

/**
 *
 * @author SE130204
 */
public class CartObj implements Serializable{
    private String customerID;
    private Map<Integer,Tbl_CarDTO> listOfCar;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Map<Integer, Tbl_CarDTO> getListOfCar() {
        return listOfCar;
    }

    public CartObj() {
    }
    
    public void addNewCarToCart(Tbl_CarDTO dto){
        if(this.listOfCar == null){
            listOfCar = new HashMap<>();
        }
        int keysize = listOfCar.size();
        for (Map.Entry<Integer, Tbl_CarDTO> entry : listOfCar.entrySet()) {
            Integer key = entry.getKey();
            Tbl_CarDTO values = entry.getValue();
            Timestamp rentVar = Timestamp.valueOf(values.getC_dateRent());
            Timestamp returnVar = Timestamp.valueOf(values.getC_dateReturn());
            if(dto.getC_id() == values.getC_id()){
                if(rentVar.equals(Timestamp.valueOf(dto.getC_dateRent())) && returnVar.equals(Timestamp.valueOf(dto.getC_dateReturn()))){
                    values.setC_amount(values.getC_amount() + dto.getC_amount());
                    this.listOfCar.put(key, values);
                    return;
                }
            }
        }
        this.listOfCar.put(keysize+1, dto);
    }
    
    public void deleteCarFromCart(int key){
        if(this.listOfCar == null){
            return;
        }
        if(this.listOfCar.containsKey(key)){
            this.listOfCar.remove(key);
            if(this.listOfCar.isEmpty()){
                this.listOfCar = null;
            }else if(key <= this.listOfCar.size()){
                for (int i = key; i < this.listOfCar.size()+1; i++) {
                    this.listOfCar.put(i, this.listOfCar.remove(i+1));
                }
            } 
        }
    }
    
    public void updateAmountOfCarInCart(int key, int amount){
        if(this.listOfCar == null){
            return;
        }
        if(this.listOfCar.containsKey(key)){
            Tbl_CarDTO dto = this.listOfCar.get(key);
            dto.setC_amount(amount);
            dto.setC_status(null);
            this.listOfCar.put(key, dto);
        }
    }
}
