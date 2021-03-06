/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.test.runmode;

import org.junit.Test;

public class ShardingToClientServerTest extends RunModeTest {

    public ShardingToClientServerTest() {
        // setHost("127.0.0.2");
    }

    @Test
    @Override
    public void run() throws Exception {
        String dbName = ShardingToClientServerTest.class.getSimpleName();
        executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName
                + " RUN MODE sharding WITH REPLICATION STRATEGY (class: 'SimpleStrategy', replication_factor: 1)"
                + " PARAMETERS (nodes=2)");

        crudTest(dbName);

        executeUpdate("ALTER DATABASE " + dbName + " RUN MODE client_server");
    }
}
