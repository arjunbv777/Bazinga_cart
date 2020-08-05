import React from 'react';

import ProductsGrid from './ProductsGrid';
import Cart from '../cart';
import 'bootswatch/dist/lux/bootstrap.css'

const Store = () => {
    
    return ( 
        <div className="row" >
             <div className="col-sm-12">
             <div className="row" >
             <div className="col-sm-10">
                <div className="text-center mt-5">
                    <h1>Bazinga Shopings</h1>
                </div>
                </div>
                <div className="col-sm-2">
                <form action="/logout" method="post">
                        <input type="submit" value="Logout" />
                </form>
                </div >
            </div>
            </div>


            <div className="col-sm-6">
                <div className="text-center mt-5">
                    <h1>Store</h1>
                </div>
                <ProductsGrid/>
            </div>
            
            <div className="col-sm-6">
                <div className="text-center mt-5">
                    <h1>Order Details</h1>
                </div>
                <Cart/>
            </div>

        </div>
     );
}
 
export default Store;