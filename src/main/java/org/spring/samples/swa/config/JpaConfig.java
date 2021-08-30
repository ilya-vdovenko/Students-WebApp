/*
 * Copyright 2019-2021, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spring.samples.swa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
* Configuration persist layer for JPA profile.
*
* @author Ilya Vdovenko
*/

@Configuration
@Profile("jpa")
@ComponentScan("org.spring.samples.swa.repository.jpa")
public class JpaConfig {
    
}
