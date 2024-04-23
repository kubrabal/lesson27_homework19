package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.*;
import pages.WebTablesPageElements;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WebTablesPageSteps extends WebTablesPageElements {

    @Given("I open the web tables page")
    public void navigateAndAssert () {
        open("https://demoqa.com/webtables");
        $("#addNewRecordButton").should(Condition.exist);
        assertThat("https://demoqa.com/webtables");
    }

    @When("I add a new record")
    public void addNewRecord() {
        addNewRecordButton.scrollTo().click();
        registrationForm.shouldBe(Condition.visible);

        //Setting form fields
        firstNameField.setValue("Joh");
        lastNameField.setValue("Doe");
        emailField.setValue("john.doe@email.com");
        ageField.setValue("25");
        salaryField.setValue("35000");
        departmentField.setValue("Sales");

        submitButton.click();
    }

    @Then("I should see the newly added record in the table")
    public void addedRecordIsVisible() {
        lastAddedEntry.shouldBe(Condition.visible);
    }

    @When("I edit the added record")
    public void editNewRecord () {
        editButton.click();

        firstNameField.clear();
        firstNameField.setValue("John");
        submitButton.click();
    }

    @Then("I should see the edited record in the table")
    public void theRecordShouldBeUpdated() {
        System.out.println("Field modified as: " + $(".rt-tbody > div:nth-child(4) > div > div:nth-child(1)").getText());
    }

    @AfterTest
    public void tearDown() {
    }

}