import React, { useContext } from 'react';

import { CartContext } from '../../contexts/CartContext';
import { formatNumber } from '../../helpers/utils';

const ProductItem = ({product}) => {

    const { addProduct, cartItems, increase } = useContext(CartContext);

    const isInCart = product => {
        return !!cartItems.find(item => item.id === product.id);
    }

    const existingCartItem = product => {  increase(cartItems.find(item => item.id === product.id)); }

    return ( 
        <div className="card card-body">
            <p>{product.name}</p>
            <h3 className="text-left">{formatNumber(product.price)}</h3>
            <p> Available Stock: {product.quantity}</p>
            { product.quantity != 0 &&
            <div className="text-right">
                {
                    isInCart(product) && 
                    <button 
                    onClick={() => existingCartItem(product)}
                    className="btn btn-outline-primary btn-sm">Add more</button>
                }

                {
                    !isInCart(product) && 
                    <button 
                    onClick={() => addProduct(product)}
                    className="btn btn-primary btn-sm">Add to cart</button>
                }
               
            </div>
            } 
        </div>
     );
}
 
export default ProductItem;