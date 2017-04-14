/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.app.web.wx.mail;

import java.util.List;

import org.dbflute.utflute.lastaflute.mail.TestingPreparedMessage;
import org.docksidestage.mylasta.mail.whitebox.LoopBeansPostcard;
import org.docksidestage.unit.UnitFortressTestCase;
import org.lastaflute.web.response.JsonResponse;

/**
 * @author jflute
 */
public class WxMailActionTest extends UnitFortressTestCase {

    public void test_loopbeans() {
        // ## Arrange ##
        WxMailAction action = new WxMailAction();
        inject(action);
        reserveMailAssertion(mailData -> {
            List<TestingPreparedMessage> messageList = mailData.required(LoopBeansPostcard.class);
            assertHasOnlyOneElement(messageList);
            TestingPreparedMessage message = messageList.get(0);
            message.assertPlainTextContains("mystic, hangar");
        });

        // ## Act ##
        JsonResponse<String> response = action.loopbeans();

        // ## Assert ##
        validateJsonData(response);
    }
}
