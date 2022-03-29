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
package rafael.alcocer.caldera.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rafael.alcocer.caldera.model.KeyValue;
import rafael.alcocer.caldera.service.RedisService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/setProperty")
    @ResponseBody
    public void setProperty(@RequestBody KeyValue keyValue) {
        redisService.setProperty(keyValue.getKey(), keyValue.getValue());
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestBody KeyValue keyValue) {
        return redisService.delete(keyValue.getKey());
    }

    @GetMapping("/exists")
    @ResponseBody
    public boolean exists(@RequestBody KeyValue keyValue) {
        return redisService.exists(keyValue.getKey());
    }
}
