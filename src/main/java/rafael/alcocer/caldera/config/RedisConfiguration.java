/**
 * Copyright [2021] [RAFAEL ALCOCER CALDERA]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rafael.alcocer.caldera.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.convertor.TypeConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedissonClient redissonClient() {
        // connects to 127.0.0.1:6379 by default
        return Redisson.create();
    }

    @Bean
    public TypeConvertor typeConvertor() {
        return new TypeConvertor();
    }
}
