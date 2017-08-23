package rewards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * This is unit test to verify that the different components of RewardNetwork work together for dining.
 *
 * Created by abhijeet on 8/23/2017.
 */
@Import(RewardNetwork.class)
public class RewardNetworkTests {

    RewardNetwork rewardNetwork;

    @Before
    public void setUp() {
        // Create the test configuration for application
        ApplicationContext applicationContext = SpringApplication.run(TestInfrastructureConfig.class);
        // Get bean from application context
        rewardNetwork = applicationContext.getBean(RewardNetwork.class);
    }

    @Test
    public void testRewardForDining() {
        // create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
        Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

        // call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
        RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);

        // assert the expected reward confirmation results
        assertNotNull(confirmation);
        assertNotNull(confirmation.getConfirmationNumber());

        // assert an account contribution was made
        AccountContribution contribution = confirmation.getAccountContribution();
        assertNotNull(contribution);

        // the account number should be '123456789'
        assertEquals("123456789", contribution.getAccountNumber());

        // the total contribution amount should be 8.00 (8% of 100.00)
        assertEquals(common.money.MonetaryAmount.valueOf("8.00"), contribution.getAmount());

        // the total contribution amount should have been split into 2 distributions
        assertEquals(2, contribution.getDistributions().size());

        // each distribution should be 4.00 (as both have a 50% allocation)
        assertEquals(common.money.MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
        assertEquals(common.money.MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
    }
}
