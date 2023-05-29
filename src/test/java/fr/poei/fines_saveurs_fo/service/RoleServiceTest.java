package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RoleServiceTest {

    @InjectMocks
    private RoleService underTest;
    @Mock
    private RoleRepository roleRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldReturnOptRoleGivenId() {
        long id = 1L;
        Optional<Role> expectedRole = Optional.of(new Role());
        expectedRole.get().setId((int) id);

        when(roleRepositoryMock.findById(id)).thenReturn(expectedRole);

        Optional<Role> result = underTest.findById(id);

        assertEquals(expectedRole, result);
    } // findById

    @Test
    public void ShouldReturnOptRoleGivenName() {
        String name = "USER";
        Optional<Role> expectedRole = Optional.of(new Role());
        expectedRole.get().setId(1);
        expectedRole.get().setName(name);

        when(roleRepositoryMock.findByName(name)).thenReturn(expectedRole);

        Optional<Role> result = underTest.findByName(name);

        assertEquals(expectedRole, result);
    } // findByName
}