
import com.assignmentsmobile.assignment_2.data.SearchedFilms

import com.assignmentsmobile.assignment_2.data.domain.FilmsSearchApiService

class SearchFilmsRepository(private val apiService: FilmsSearchApiService){
    suspend fun getFilmImages(keyword: String, page: Int): Result<SearchedFilms>{
            return try{
                val res = apiService.getFilmByKeyword(keyword, page)
                if(res.isSuccessful){
                    Result.success(res.body()!!)
                }else{
                    Result.failure(Throwable("Error: ${res.message()}"))
                }

            }catch (e: Exception){
                Result.failure(e)
            }
    }
}
