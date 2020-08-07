import React, { createContext, useReducer,  useEffect } from "react";
import { CartReducer, sumItems } from "./CartReducer";
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
        res.data.forEach((e) => {
          addProductData(e);
        });
      }, [])
      .catch((error) => console.log(error));

    axios
      .get("/orders")
      .then((res) => {
        res.data.forEach((e) => {
          e.product.orderID = e.id;
          addItemsToCart(e.product);
          for (let count = 1; count < e.orderQuantity; count++) {
            increaseItemInCart(e.product);
          }
        });
      }, [])
      .catch((error) => console.log(error));

    },[]);

  const addProductData = (payload) => {
    dispatch({ type: "ADD_PRODUCT", payload });
  };

  const addItemsToCart = (payload) => {
    dispatch({ type: "ADD_ITEM", payload });
  };

  const increaseItemInCart = (payload) => {
    dispatch({ type: "INCREASE", payload });
  };
  const increase = (payload) => {
    let url= "/updateorder/"+payload.orderID
    axios
    .get(url)
    .then((res) => {
      res.data.forEach((e) => {
        addProductData(e);       
      });
      increaseItemInCart(payload );
    }, [])
    .catch((error) => console.log(error));
  };

  const decrease = (payload) => {

    let url= "/removeorder/"+payload.orderID
    axios
    .get(url)
    .then((res) => {
      res.data.forEach((e) => {
        addProductData(e);    
      });
      dispatch({ type: "DECREASE", payload });
    }, [])
    .catch((error) => console.log(error));
    
  };

  const addProduct = (payload) => {
    axios
      .post("/order", payload)
      .then((res) => {
        payload.orderID = res.data.id;
        addItemsToCart(payload);
      }, [])
      .catch((error) => console.log(error));

      axios
      .get("/products")
      .then((res) => {
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
      dispatch({ type: "REMOVE_ITEM", payload });
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
