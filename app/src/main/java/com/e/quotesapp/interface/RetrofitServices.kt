import com.e.quotesapp.model.Quote
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("quotes")
    fun getRandomQuotes(): Call<ArrayList<Quote>>

    @GET("quotes/anime?")
    fun getQuotesByTitle(@Query("title") name: String): Call<ArrayList<Quote>>
}