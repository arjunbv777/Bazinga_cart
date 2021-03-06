import React, { useContext } from 'react';
import { CartContext } from '../../contexts/CartContext';

import { formatNumber } from '../../helpers/utils';

const CartItem = ({product}) => {

    const { increase, decrease, removeProduct } = useContext(CartContext);

    return ( 
        <div className="row no-gutters py-2">
     
            <div className="col-sm-4 p-2">
                <h5 className="mb-1">{product.name}</h5>
                <p className="mb-1">Price: {formatNumber(product.price)} </p> 
            </div>

            <div className="col-sm-2 p-2 text-center ">
                 <p className="mb-0">Qty: {product.quantity}</p>
            </div>

            <div className="col-sm-4 p-2 text-right">
                 <button onClick={() => increase(product)}>
                     Add
                 </button>

                 {
                     product.quantity > 1 &&
                     <button onClick={() => decrease(product)}>
                        Remove
                    </button>
                 }

                {
                     product.quantity === 1 &&
                     <button onClick={() => removeProduct(product)}>
                        Delete
                    </button>
                 }
                 
            </div>
        </div>
     );
}
 
export default CartItem;