package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final IngredientType ingredientType;
    private final String expectedType;

    public BurgerReceiptTest(IngredientType ingredientType, String expectedType) {
        this.ingredientType = ingredientType;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        });
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(ingredient.getName()).thenReturn("Cheese");
        when(ingredient.getPrice()).thenReturn(50.0f);
        when(ingredient.getType()).thenReturn(ingredientType);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt =
                String.format("(==== Black Bun ====)%n") +
                        String.format("= %s Cheese =%n", expectedType) +
                        String.format("(==== Black Bun ====)%n") +
                        String.format("%n") +
                        String.format("Price: %f%n", 250.0f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
