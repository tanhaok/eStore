import React, { useState, useEffect } from 'react'
import { ProductCreateType, ProductImageType, ProductThumbnail } from '../../../../types/ProductType'
import { UseFormSetValue, UseFormGetValues } from 'react-hook-form'

interface props {
    setFunc: UseFormSetValue<ProductCreateType>
    getFunc: UseFormGetValues<ProductCreateType>
}

const ProductImage = ({ setFunc, getFunc }: props) => {
    const [thumbnail, setThumbnail] = useState<ProductThumbnail>({ url: '' })
    const [images, setImages] = useState<ProductImageType>({ urls: [], files: [] })

    useEffect(() => {
        const thumbnailUrl = getFunc('thumbnail.url')
        const thumbnailFile = getFunc('thumbnail.file')
        setThumbnail({ url: thumbnailUrl, file: thumbnailFile })

        const imagesUrls = getFunc('images.urls')
        const imagesFiles = getFunc('images.files')

        setImages({ urls: imagesUrls, files: imagesFiles })
    }, [])

    const onUploadThumbnail = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const src = URL.createObjectURL(e.target.files[0])
            setThumbnail({ file: e.target.files[0], url: src })
            setFunc('thumbnail', { file: e.target.files[0], url: src })
        }
    }

    const onUploadImage = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const _urls: string[] = []
            const _files: File[] = []

            const fileList = [...e.target.files]
            fileList.forEach((item) => {
                const url = URL.createObjectURL(item)
                _urls.push(url)
                _files.push(item)
            })

            setImages({ urls: _urls, files: _files })
            setFunc('images.urls', _urls)
            setFunc('images.files', _files)
        }
    }

    return (
        <div className='product-attribute w-100'>
            <div className='thumbnail '>
                <div className='input-group mb-3'>
                    <label className='input-group-text' htmlFor='inputThumbnail'>
                        Upload Thumbnail
                    </label>
                    <input
                        type='file'
                        className='form-control'
                        id='inputThumbnail'
                        accept='image/*'
                        onChange={(e) => onUploadThumbnail(e)}
                    />
                </div>
                <div>{thumbnail.url && <img src={thumbnail.url} width={250} height={250} />}</div>
            </div>
            <div className='image'>
                <div className='input-group mb-3'>
                    <label className='input-group-text' htmlFor='inputProductImage'>
                        Upload Product Image
                    </label>
                    <input
                        type='file'
                        multiple
                        className='form-control'
                        id='inputProductImage'
                        accept='image/*'
                        onChange={(e) => onUploadImage(e)}
                    />
                </div>
                <div>
                    {(images.urls || []).map((item, index) => (
                        <img src={item} width={250} height={250} key={index} />
                    ))}
                </div>
            </div>
        </div>
    )
}

export default ProductImage
