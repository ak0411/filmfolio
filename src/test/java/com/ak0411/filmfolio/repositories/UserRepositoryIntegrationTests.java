package com.ak0411.filmfolio.repositories;

import com.ak0411.filmfolio.TestDataUtil;
import com.ak0411.filmfolio.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTests {
    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTests(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        User user = TestDataUtil.createTestUserAlice();
        underTest.save(user);
        Optional<User> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
//        underTest.save(authorEntityA);
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        underTest.save(authorEntityB);
//        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
//        underTest.save(authorEntityC);
//
//        Iterable<AuthorEntity> result = underTest.findAll();
//        assertThat(result)
//                .hasSize(3).
//                containsExactly(authorEntityA, authorEntityB, authorEntityC);
//    }
//
//    @Test
//    public void testThatAuthorCanBeUpdated() {
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
//        underTest.save(authorEntityA);
//        authorEntityA.setName("UPDATED");
//        underTest.save(authorEntityA);
//        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorEntityA);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted() {
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
//        underTest.save(authorEntityA);
//        underTest.deleteById(authorEntityA.getId());
//        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
//        assertThat(result).isEmpty();
//    }
//
//    @Test
//    public void testThatGetAuthorsWithAgeLessThan() {
//        AuthorEntity testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
//        underTest.save(testAuthorAEntity);
//        AuthorEntity testAuthorBEntity = TestDataUtil.createTestAuthorB();
//        underTest.save(testAuthorBEntity);
//        AuthorEntity testAuthorCEntity = TestDataUtil.createTestAuthorC();
//        underTest.save(testAuthorCEntity);
//
//        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
//        assertThat(result).containsExactly(testAuthorBEntity, testAuthorCEntity);
//    }
//
//    @Test
//    public void testThatGetAuthorsWithAgeGreaterThan() {
//        AuthorEntity testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
//        underTest.save(testAuthorAEntity);
//        AuthorEntity testAuthorBEntity = TestDataUtil.createTestAuthorB();
//        underTest.save(testAuthorBEntity);
//        AuthorEntity testAuthorCEntity = TestDataUtil.createTestAuthorC();
//        underTest.save(testAuthorCEntity);
//
//        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);
//        assertThat(result).containsExactly(testAuthorAEntity);
//    }
}
