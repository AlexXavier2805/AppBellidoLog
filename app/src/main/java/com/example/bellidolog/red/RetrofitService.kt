package com.example.bellidolog.red

import com.example.bellidolog.model.entity.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    @POST("authenticate")
    fun authenticate(@Body jwtRequest: JwtRequest) : Call<JwtResponse>

    @GET("matricula/find/{username}")
    fun findByUsername(@Header("Authorization") token:String, @Path("username") username:String) : Call<MatriculaEntity>

    @GET("curso/find/{id}")
    fun findCoursesByGrade(@Header("Authorization") token: String, @Path("id") gradoId: Int) : Call<List<CursoEntity>>

    @GET("calificacion/find/{id}")
    fun findCalificationById(@Header("Authorization") token: String, @Path("id") matriculaId: Int) : Call<List<NotaEntity>>

    @GET("horario/find/{id}")
    fun findScheduleById(@Header("Authorization") token: String, @Path("id") seccionId: Int) : Call<List<HorarioEntity>>

    @PUT("alumno/actualizar")
    fun updateAlumn(@Header("Authorization") token: String, @Body alumnoEntity: AlumnoEntity) : Call<Unit>

    @PUT("usuario/actualizar")
    fun updateUser(@Header("Authorization") token: String, @Body usuario: UsuarioEntity) : Call<Unit>

    companion object{
        private var retrofietService: RetrofitService? = null

        fun getIntance(): RetrofitService{
            if(retrofietService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://mariaparadobellido.herokuapp.com/rest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofietService = retrofit.create(RetrofitService::class.java)
            }
            return retrofietService!!
        }
    }
}