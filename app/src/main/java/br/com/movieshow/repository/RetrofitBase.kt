package br.com.movieshow.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

private class AddHeaderInterceptor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

private class AddAPIInterceptor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val url = chain.request().url
        val urlBuilder = url.newBuilder()

        val apiKey = "60dedd5a664205ede6cff0c6d4d64e80"
        urlBuilder.addQueryParameter("api_key", apiKey)
        urlBuilder.addQueryParameter("language", "pt-BR")
        builder.url(urlBuilder.build())
        return chain.proceed(builder.build())
    }
}

class JavaDateTypeAdapter : TypeAdapter<Date>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) out.nullValue() else out.value(value.time)
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader?): Date? {
        return if (`in` != null) {
            val vl = `in`.nextLong()
            Date(vl)
        }
        else null
    }
}

open class RetrofitBase(context: Context, baseUrl: String) {
    val retrofit: Retrofit
    val gson: Gson

    init {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(AddHeaderInterceptor(context))
            .addInterceptor(AddAPIInterceptor(context))
            .addInterceptor(logInterceptor)
            .build()

        gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, JavaDateTypeAdapter())
            .create()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}