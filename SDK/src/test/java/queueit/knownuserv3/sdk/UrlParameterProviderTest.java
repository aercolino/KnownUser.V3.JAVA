package queueit.knownuserv3.sdk;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UrlParameterProviderTest {

    @Test
    public void TryExtractQueueParams_Test() {
        String queueitToken = "ts_1480593661~cv_10~ce_false~q_944c1f44-60dd-4e37-aabc-f3e4bb1c8895~c_customerid~e_eventid~rt_disabled~h_218b734e-d5be-4b60-ad66-9b1b326266e2";

        QueueUrlParams queueParameter = QueueParameterHelper.extractQueueParams(queueitToken);
        assertTrue(queueParameter.getTimeStamp() == 1480593661);
        assertTrue("eventid".equals(queueParameter.getEventId()));
        assertTrue(queueParameter.getCookieValidityMinutes() == 10);
        assertTrue(queueParameter.getExtendableCookie() == false);
        assertTrue("944c1f44-60dd-4e37-aabc-f3e4bb1c8895".equals(queueParameter.getQueueId()));
        assertTrue("218b734e-d5be-4b60-ad66-9b1b326266e2".equals(queueParameter.getHashCode()));
        assertTrue(queueitToken.equals(queueParameter.getQueueITToken()));
        assertTrue("ts_1480593661~cv_10~ce_false~q_944c1f44-60dd-4e37-aabc-f3e4bb1c8895~c_customerid~e_eventid~rt_disabled".equals(queueParameter.getQueueITTokenWithoutHash()));
    }

    @Test
    public void TryExtractQueueParams_NotValidFormat_Test() {
        String queueitToken = "ts_sasa~cv_adsasa~ce_falwwwse~q_944c1f44-60dd-4e37-aabc-f3e4bb1c8895~h_218b734e-d5be-4b60-ad66-9b1b326266e2";
        String queueitTokenWithoutHash = "ts_sasa~cv_adsasa~ce_falwwwse~q_944c1f44-60dd-4e37-aabc-f3e4bb1c8895";

        QueueUrlParams queueParameter = QueueParameterHelper.extractQueueParams(queueitToken);
        assertTrue(queueParameter.getTimeStamp() == 0);
        assertTrue("".equals(queueParameter.getEventId()));
        assertTrue(queueParameter.getCookieValidityMinutes() == null);
        assertTrue(queueParameter.getExtendableCookie() == false);
        assertTrue("944c1f44-60dd-4e37-aabc-f3e4bb1c8895".equals(queueParameter.getQueueId()));
        assertTrue("218b734e-d5be-4b60-ad66-9b1b326266e2".equals(queueParameter.getHashCode()));
        assertTrue(queueitToken.equals(queueParameter.getQueueITToken()));
        assertTrue(queueitTokenWithoutHash.equals(queueParameter.getQueueITTokenWithoutHash()));
    }

    @Test
    public void TryExtractQueueParams_Using_QueueitToken_With_No_Values_Test() {
        String queueitToken = "e~q~ts~ce~rt~h";

        QueueUrlParams queueParameter = QueueParameterHelper.extractQueueParams(queueitToken);
        assertTrue(queueParameter.getTimeStamp() == 0);
        assertTrue("".equals(queueParameter.getEventId()));
        assertTrue(queueParameter.getCookieValidityMinutes() == null);
        assertTrue(queueParameter.getExtendableCookie() == false);
        assertTrue("".equals(queueParameter.getQueueId()));
        assertTrue("".equals(queueParameter.getHashCode()));
        assertTrue(queueitToken.equals(queueParameter.getQueueITToken()));
        assertTrue(queueitToken.equals(queueParameter.getQueueITTokenWithoutHash()));
    }

    @Test
    public void TryExtractQueueParams_Using_No_QueueitToken_Expect_Null() {        
        QueueUrlParams queueParameter = QueueParameterHelper.extractQueueParams("");
        assertTrue(queueParameter == null);
    }
}
