package org.motechproject.ebodac.osgi;

import com.google.common.base.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.motechproject.ebodac.validation.ValidationError;
import org.motechproject.ebodac.web.SubmitSubjectRequest;

import java.util.List;

import static com.google.common.collect.Iterables.any;

/**
 * Tests SubmitSubjectRequest validations
 */
public class ValidationTest {

    private Predicate<ValidationError> hasError(final String errorMessage)  {
        return new Predicate<ValidationError>() {
            @Override
            public boolean apply(ValidationError err) {
                return err.getMessage().equals(errorMessage);
            }
        };
    }

    @Test
    public void testSubjectIdValidation() {

        SubmitSubjectRequest submitRequestWithCorrectId = new SubmitSubjectRequest();
        SubmitSubjectRequest submitRequestWithWrongId = new SubmitSubjectRequest();

        submitRequestWithCorrectId.setSubjectId("1010000172");
        submitRequestWithWrongId.setSubjectId("1010000173");

        List<ValidationError> request1Errors = submitRequestWithCorrectId.validate();
        List<ValidationError> request2Errors = submitRequestWithWrongId.validate();

        Assert.assertTrue(!any(request1Errors, hasError(ValidationError.SUBJECT_ID_NOT_VERIFIED)));
        Assert.assertFalse(!any(request2Errors, hasError(ValidationError.SUBJECT_ID_NOT_VERIFIED)));
    }

    @Test
    public void testSubmitRequestNullValuesValidation() {
        SubmitSubjectRequest request = new SubmitSubjectRequest();

        List<ValidationError> requestErrors = request.validate();

        Assert.assertTrue(any(requestErrors, hasError(ValidationError.LANGUAGE_NULL)));
        Assert.assertTrue(any(requestErrors, hasError(ValidationError.SUBJECT_ID_NULL)));
        Assert.assertTrue(any(requestErrors, hasError(ValidationError.NAME_NULL)));
    }

    @Test
    public void testLanguageCodeValidation() {
        SubmitSubjectRequest request = new SubmitSubjectRequest();
        SubmitSubjectRequest request2 = new SubmitSubjectRequest();

        request.setLanguage("not-en");
        request2.setLanguage("kri");

        List<ValidationError> requestErrors = request.validate();
        List<ValidationError> request2Errors = request2.validate();

        Assert.assertTrue(any(requestErrors, hasError(ValidationError.LANGUAGE_NOT_CORRECT)));
        Assert.assertFalse(any(request2Errors, hasError(ValidationError.LANGUAGE_NOT_CORRECT)));
    }

    @Test
    public void testValuesWithNumbers() {
        SubmitSubjectRequest request = new SubmitSubjectRequest();

        request.setSubjectId("1231QWE3463asd45");
        request.setName("King Lion 3rd");
        request.setHouseholdName("Brown34");
        request.setHeadOfHousehold("M4ri0n");

        List<ValidationError> requestErrors = request.validate();

        Assert.assertTrue(any(requestErrors, hasError(ValidationError.SUBJECT_ID_NOT_NUMERIC)));
        Assert.assertTrue(any(requestErrors, hasError(ValidationError.NAME_HAS_DIGITS)));
        Assert.assertTrue(any(requestErrors, hasError(ValidationError.HOUSEHOLD_NAME_HAS_DIGITS)));
        Assert.assertTrue(any(requestErrors, hasError(ValidationError.HEAD_OF_HOUSEHOLD_HAS_DIGITS)));
    }
}