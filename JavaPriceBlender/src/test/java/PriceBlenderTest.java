import com.home.test.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;


public class PriceBlenderTest {

    @BeforeAll
    static void setup(){

    }

    @Test
    void testBestBidAskMid()
    {
        PriceBlender blender = new PriceBlenderImpl("MSFT");
        blender.UpdatePrice(25,28, MarketSource.SOURCE_A);
        blender.UpdatePrice(26,29, MarketSource.SOURCE_B);
        blender.UpdatePrice(27,30, MarketSource.SOURCE_C);

        Assertions.assertEquals(27, blender.getBestBid() );
        Assertions.assertEquals(28, blender.getBestAsk() );
        Assertions.assertEquals(27.5, blender.getBestMid() );
    }

    @Test
    void testBidAskCrossing() {

        PriceBlender blender = new PriceBlenderImpl("MSFT");
        blender.UpdatePrice(25,26, MarketSource.SOURCE_A);
        blender.UpdatePrice(26,29, MarketSource.SOURCE_B);
        blender.UpdatePrice(27,30, MarketSource.SOURCE_C);

        Assertions.assertEquals(0, blender.getBestBid() );
        Assertions.assertEquals(0, blender.getBestAsk() );
        Assertions.assertEquals(0, blender.getBestMid() );

    }


    @Test
    void testBidAskZeroFromAllSource() {

        PriceBlender blender = new PriceBlenderImpl("MSFT");
        blender.UpdatePrice(25,26, MarketSource.SOURCE_A);
        blender.UpdatePrice(26,29, MarketSource.SOURCE_B);
        blender.UpdatePrice(27,30, MarketSource.SOURCE_C);

        blender.UpdatePrice(0,0, MarketSource.SOURCE_A);
        blender.UpdatePrice(0,0, MarketSource.SOURCE_B);
        blender.UpdatePrice(0,0, MarketSource.SOURCE_C);

        Assertions.assertEquals(0, blender.getBestBid() );
        Assertions.assertEquals(0, blender.getBestAsk() );
        Assertions.assertEquals(0, blender.getBestMid() );

    }

    @Test
    void testInvalidateMidIFBidIsZeroFromSameSource() {

        PriceBlender blender = new PriceBlenderImpl("MSFT");
        blender.UpdatePrice(25,26, MarketSource.SOURCE_A);
        blender.UpdatePrice(0,29, MarketSource.SOURCE_A);

        Assertions.assertEquals(0, blender.getBestBid() );
        Assertions.assertEquals(26, blender.getBestAsk() );
        Assertions.assertEquals(0, blender.getBestMid() );

    }





}


