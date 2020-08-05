import React, { useContext } from 'react';
import ProductItem from './ProductItem';
import styles from './ProductsGrid.module.scss';
import { CartContext } from '../../contexts/CartContext';

const ProductsGrid = () => {

    const { products} = useContext(CartContext)

    return ( 
        <div className={styles.p__container}>
            <div className="row">
                <div className="col-sm-6">
                    <div className="py-2">
                        {products.length} Products
                    </div>
                </div>
            </div>
            <div className={styles.p__grid}>

                {
                    products.map(product => (
                        <ProductItem key={product.id} product={product}/>
                    ))
                }
            </div>
        </div>
     );
}
 
export default ProductsGrid;