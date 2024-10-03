package by.polly.beatyshop.modules.product.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient(autoRegister = false)
public class DiscoveryConfiguration {
}
