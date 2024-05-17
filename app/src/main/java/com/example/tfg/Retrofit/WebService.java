package com.example.tfg.Retrofit;
import com.example.tfg.Retrofit.DataClases.Mesa;
import com.example.tfg.Retrofit.DataClases.Producto;
import com.example.tfg.Retrofit.DataClases.Reserva;
import com.example.tfg.Retrofit.DataClases.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebService {
    //MESAS
    @GET("mesas")
    Call<List<Mesa>> getMesas();
    @GET("mesas/{id}")
    Call<Mesa> getMesa(@Path("id") String idMesa);
    @POST("mesas")
    Call<Mesa> createMesa(@Body Mesa newMesa);
    @PUT("mesas/{id}")
    Call<Mesa> updateMesa(@Path("id") String idMesa, @Body Mesa mesa);
    @DELETE("mesas/{id}")
    Call<Void> deleteMesa(@Path("id") String idMesa);
    @GET("mesas/ocupadas")
    Call<List<Mesa>> getMesasOcupadas();
    @PUT("mesas/{id}/estado")
    Call<Mesa> updateEstadoMesa(@Path("id") String idMesa, @Body Mesa mesa);
    @POST("mesas/{id}/productos")
    Call<Mesa> addProductoAMesa(@Path("id") String idMesa, @Body Producto producto);
    @DELETE("mesas/{id}/productos")
    Call<Mesa> removeProductoDeMesa(@Path("id") String idMesa, @Body Producto producto);


    //RESERVAS
    @GET("reservas")
    Call<List<Reserva>> getReservas();

    @GET("reservas/{id}")
    Call<Reserva> getReserva(@Path("id") String idReserva);

    @POST("reservas")
    Call<Reserva> createReserva(@Body Reserva newReserva);

    @PUT("reservas/{id}")
    Call<Reserva> updateReserva(@Path("id") String idReserva, @Body Reserva reserva);

    @DELETE("reservas/{id}")
    Call<Void> deleteReserva(@Path("id") String idReserva);

    @GET("reservas/fecha/{fecha}")
    Call<List<Reserva>> getReservasPorFecha(@Path("fecha") String fecha);

    //USUARIOS
    @POST("users/signup")
    Call<User> signUp(@Body User newUser);

    @POST("users/signin")
    Call<User> signIn(@Body User user);
}
