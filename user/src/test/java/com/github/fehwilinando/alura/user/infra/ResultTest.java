package com.github.fehwilinando.alura.user.infra;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void resultCanBeFail() {
        assertNotNull(Result.fail());
    }

    @Test
    public void resultCanBeSuccess() {
        assertNotNull(Result.success(1));
    }

    @Test
    public void whenResultIsFailShouldReturnFalseToIsSuccessAndTrueToIsFail() {
        Result<Integer> result = Result.fail();

        assertFalse(result.isSuccess());
        assertTrue(result.isFail());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwNoSuchElementExceptionWhenTryingToGetContentOfFailedResult() {
        Result<Integer> result = Result.fail();

        result.getContent();
    }

    @Test
    public void whenResultIsSuccessShouldReturnTrueToIsSuccessAndFalseToIsFail() {
        Result<Integer> result = Result.success(1);

        assertTrue(result.isSuccess());
        assertFalse(result.isFail());
    }

    @Test
    public void shouldGetContentWhenResultIsSuccess() {
        Result<Long> result = Result.success(1L);

        Long content = result.getContent();

        assertEquals(Long.valueOf(1L), content);
    }

    @Test
    public void givenASuccessResultCanBePossibleToMapHimToAnotherResult() {
        Result<Long> result = Result.success(1L);

        Result<Long> otherResult = result.onSuccessMap(n -> n + 1L);

        assertNotNull(otherResult);
        assertTrue(otherResult.isSuccess());
        assertFalse(otherResult.isFail());
        assertEquals(Long.valueOf(2L), otherResult.getContent());
    }

    @Test
    public void failResultAlwaysMappingToFailResult() {
        Result<Long> result = Result.fail();

        Result<Long> otherResult = result.onSuccessMap(n -> n + 1);

        assertFalse(otherResult.isSuccess());
        assertTrue(otherResult.isFail());

        Result<Long> anotherResult = otherResult.onSuccessGet(() -> 2L);

        assertFalse(anotherResult.isSuccess());
        assertTrue(anotherResult.isFail());
    }

    @Test
    public void givenASuccessResultCanBePossibleToSupplyValueToAnotherResult() {
        Result<Long> result = Result.success(1L);

        Result<Long> otherResult = result.onSuccessGet(() -> 2L);

        assertNotNull(otherResult);
        assertTrue(otherResult.isSuccess());
        assertFalse(otherResult.isFail());
        assertEquals(Long.valueOf(2L), otherResult.getContent());
    }

    @Test
    public void givenAFailResultShouldBePossibleToSupplyAValueToHim() {
        Result<Long> result = Result.fail();

        Long content = result.orIfFailGet(() -> 1L);

        assertFalse(result.isSuccess());
        assertTrue(result.isFail());
        assertEquals(Long.valueOf(1L), content);
    }

    @Test
    public void whenResultIsSuccessAndSupplyAFailedValueThenReturnTheSuccessContent() {
        Result<Long> result = Result.success(1L);

        Long content = result.orIfFailGet(() -> 0L);

        assertTrue(result.isSuccess());
        assertFalse(result.isFail());
        assertEquals(Long.valueOf(1L), content);
    }
}