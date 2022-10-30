package com.akagra.boiling_water_app.extra

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.GlideModule


class GlideOptionChanger: GlideModule {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        println("Glided")
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, 50000000))
    }
}