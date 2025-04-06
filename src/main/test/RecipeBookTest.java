package main.test;
import main.code.RecipeBook;
import main.code.Recipe;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
class RecipeBookTest {

    @Test
    @DisplayName("Test Adding Recipe")
    void testAddRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        boolean added = recipeBook.addRecipe(recipe);
        assertEquals(true, added, "Recipe should be added successfully.");
    }

    @Test
    @DisplayName("Test Adding Recipe When Book Is Full")
    void testAddRecipeWhenFull() {
        RecipeBook recipeBook = new RecipeBook();
        for (int i = 0; i < 4; i++) {
            recipeBook.addRecipe(new Recipe());
        }
        Recipe newRecipe = new Recipe();
        boolean added = recipeBook.addRecipe(newRecipe);
        assertEquals(false, added, "Recipe should not be added when the book is full.");
    }

    @Test
    @DisplayName("Test Adding Duplicate Recipe")
    void testAddDuplicateRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipeBook.addRecipe(recipe); 
        boolean added = recipeBook.addRecipe(recipe);
        assertEquals(false, added, "Duplicate recipe should not be added.");
    }

    @Test
    @DisplayName("Test Adding Recipe and Verifying")
    void testAddAndVerifyRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipeBook.addRecipe(recipe); 
        Recipe[] recipes = recipeBook.getRecipes();
        boolean exists = false;
        for (Recipe r : recipes) {
            if (r.equals(recipe)) {
                exists = true;
                break;
            }
        }
        assertEquals(true, exists, "Added recipe should be present in the recipe book.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Recipe1", "Recipe2", "Recipe3"})
    @DisplayName("Test Valid Recipe Names")
    void testValidRecipeNames(String recipeName) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        assertEquals(recipeName, recipe.getName(), "Recipe name should be set correctly.");
    }
    @Test
    @DisplayName("Test Delete Recipe")
    void testDeleteRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe recipe = new Recipe();
        recipe.setName("Latte");
        recipeBook.addRecipe(recipe);
        String deletedName = recipeBook.deleteRecipe(0);
        assertEquals("Latte", deletedName);
        assertNotEquals(recipe, recipeBook.getRecipes()[0]);
    }

    @Test
    @DisplayName("Test Edit Recipe")
    void testEditRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        Recipe oldRecipe = new Recipe();
        oldRecipe.setName("Espresso");
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Cappuccino");
        recipeBook.addRecipe(oldRecipe);
        String oldName = recipeBook.editRecipe(0, newRecipe);
        assertEquals("Espresso", oldName);
        assertEquals(newRecipe, recipeBook.getRecipes()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Test Timeout")
    void testTimeout() {
        assertDoesNotThrow(() -> {
            Thread.sleep(500); 
        });
    }

   
}
