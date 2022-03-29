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
package rafael.alcocer.caldera.service;

import java.util.Map;

import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.convertor.TypeConvertor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {

    private final RedissonClient redissonClient;
    private final TypeConvertor typeConvertor;

    @Override
    public void setProperty(String key, String value) {
        redissonClient.getBucket(key).set(value);
    }

    @Override
    public boolean delete(String key) {
        return redissonClient.getBucket(key).delete();
    }

    @Override
    public boolean exists(String key) {
        return redissonClient.getKeys().countExists(key) == 1;
    }

    @Override
    public Map<String, Object> getAll() {
        return null;
    }

    private void x() {
        RBucket<String> rBucket = redissonClient.getBucket("key");
    }

    private Object getValue(String key, Object obj) {
        RType type = typeConvertor.convert(obj);

        if (type != null) {
            switch (type) {
            case OBJECT:
                return redissonClient.getBucket(key).get();
            case MAP:
                return redissonClient.getMap(key);
            case LIST:
                return redissonClient.getList(key);
            case SET:
            case ZSET:
                return redissonClient.getSet(key);
            }
        }

        return null;
    }

    private Object getValue(String key, RKeys keys) {
        RType type = keys.getType(key);

        if (type != null) {
            switch (type) {
            case OBJECT:
                return redissonClient.getBucket(key).get();
            case MAP:
                return redissonClient.getMap(key);
            case LIST:
                return redissonClient.getList(key);
            case SET:
            case ZSET:
                return redissonClient.getSet(key);
            }
        }

        return null;
    }
}
