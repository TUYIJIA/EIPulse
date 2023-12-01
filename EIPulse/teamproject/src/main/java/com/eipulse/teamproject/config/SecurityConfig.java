package com.eipulse.teamproject.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //解決cors問題，如果沒加 sessionId不會存到 cookie，前端請求要加withCredentials: true

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            // 此方法設定跨來源請求的映射規則，這表示任何路徑下的請求都可以進行跨來源請求
            // allowedOrigins 定義了允許的來源
            // allowCredentials => true，表示請求中可以包含認證資訊，例如cookies或HTTP認證資訊
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173", "https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5", "http://127.0.0.1:5173/")
                        .allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public BlobServiceClient blobServiceClient(){
        //azure key
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=eipulseimages;AccountKey=J3OLfPQvTNjhsavqjIZpktnTy8hCx12b3u6t+IdLYwIJ7i/nzSGJuLgF7Do5/SPoBi5+PkLamBDF+AStcEcIBQ==;EndpointSuffix=core.windows.net";
        return new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
    }

}
