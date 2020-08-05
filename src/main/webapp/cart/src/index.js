import React from 'react';
import ReactDOM from 'react-dom';

import * as serviceWorker from './serviceWorker';

import CartContextProvider from './contexts/CartContext';
import Store from './pages/store';

ReactDOM.render(
         <CartContextProvider>
          <Store/>
        </CartContextProvider>,
  document.getElementById('root')
);

serviceWorker.unregister();
