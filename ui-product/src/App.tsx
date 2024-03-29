import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'

import 'react-toastify/dist/ReactToastify.css'
import '@fontsource/inter'
import './index.css'
import '../node_modules/react-draft-wysiwyg/dist/react-draft-wysiwyg.css'
import { Product, ProductAttribute, ProductDetail, ProductGroup, ProductOption } from './components'

const App = () => (
    <div className=''>
        <Routes>
            <Route path='all' element={<Product />}></Route>
            <Route path='detail/:slug' element={<ProductDetail />}></Route>
            <Route path='option' element={<ProductOption />}></Route>
            <Route path='attribute' element={<ProductAttribute />}></Route>
            <Route path='group' element={<ProductGroup />}></Route>
        </Routes>
        <ToastContainer />
    </div>
)

export default App
