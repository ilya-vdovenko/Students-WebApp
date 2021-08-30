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

package org.spring.samples.swa.service;

import org.spring.samples.swa.config.RootAppConfig;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Integration test using the jdbc profile.
 *
 * @author Ilya Vdovenko
 * @see AbstractInstituteServiceTests AbstractInstituteServiceTests for more details.
 */

@SpringJUnitConfig(RootAppConfig.class)
@ActiveProfiles("jdbc")
public class InstituteServiceJdbcTests extends AbstractInstituteServiceTests {

}
