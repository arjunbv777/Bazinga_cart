import React, { createContext, useReducer,  useEffect } from "react";
import { CartReducer, sumItems } from "./CartReducer";
import { dummyProducts } from "../services/dummy";
import axios from "axios";

export const CartContext = createContext();

const initialState = {
  cartItems: [],
  ...sumItems([]),
  products: [],
  checkout: false,
};




const CartContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(CartReducer, initialState);

  useEffect(() => {
    axios
      .get("/products")
      .then((res) => {
        console.log(res);
        res.data.forEach((e) => {
          addProductData(e);
        });
      }, [])
      .catch((error) => console.log(error));

    axios
      .get("/orders")
      .then((res) => {
        console.log(res);
        res.data.forEach((e) => {
          e.product.orderID = e.id;
          addProduct(e.product);
          for (let count = 1; count < e.orderQuantity; count++) {
            increase(e.product);
          }
        });
      }, [])
      .catch((error) => console.log(error));
  }, []);

  const addProductData = (payload) => {
    dispatch({ type: "ADD_PRODUCT", payload });
  };

  const increase = (payload) => {
    console.log("increse");
   
    console.log(payload);
    let url= "/updateorder/"+payload.orderID
    axios
    .get(url)
    .then((res) => {
      console.log(res);
      res.data.forEach((e) => {
        addProduct(e.product);       
      });
      dispatch({ type: "INCREASE", payload });
    }, [])
    .catch((error) => console.log(error));
  };

  const decrease = (payload) => {

    let url= "/removeorder/"+payload.orderID
    axios
    .get(url)
    .then((res) => {
      console.log(res);
      res.data.forEach((e) => {
        addProduct(e.product);    
      });
      dispatch({ type: "DECREASE", payload });
    }, [])
    .catch((error) => console.log(error));
    
  };

  const addProduct = (payload) => {
    console.log("/order" + {payload});
    axios
      .put("/order", payload)
      .then((res) => {
        payload.orderID = res.data.id;
        dispatch({ type: "ADD_ITEM", payload });
        console.log(res);
      }, [])
      .catch((error) => console.log(error));

      axios
      .get("/products")
      .then((res) => {
        console.log(res);
        res.data.forEach((e) => {
          addProductData(e);
        });
      }, [])
      .catch((error) => console.log(error));
  };

  const removeProduct = (payload) => {

    let url= "/removeorder/"+payload.orderID
    axios
    .get(url)
    .then((res) => {
      console.log(res);
      dispatch({ type: "REMOVE_ITEM", payload });
      res.data.forEach((e) => {
        addProductData(e);
      });
    }, [])
    .catch((error) => console.log(error));
 
    axios
    .get("/products")
    .then((res) => {
      console.log(res);
      res.data.forEach((e) => {
        addProductData(e);
      });
    }, [])
    .catch((error) => console.log(error));
  };

  const clearCart = () => {
    dispatch({ type: "CLEAR" });
  };

  const contextValues = {
    removeProduct,
    addProduct,
    increase,
    decrease,
    clearCart,
    addProductData,
    ...state,
  };

  return (
    <CartContext.Provider value={contextValues}>
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
