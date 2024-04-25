package edu.tcu.cs.superfrogscheduler.user;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogUserDtoToSuperfrogUserConverter;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogUserToSuperfrogUserDtoConverter;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogUserDto;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class SuperfrogUserController {
    private final SuperfrogUserService superfrogUserService;

    private final SuperfrogUserToSuperfrogUserDtoConverter superfrogUserToSuperfrogUserDtoConverter;

    private final SuperfrogUserDtoToSuperfrogUserConverter superfrogUserDtoToSuperfrogUserConverter;

    public SuperfrogUserController(SuperfrogUserService superfrogUserService, SuperfrogUserToSuperfrogUserDtoConverter superfrogUserToSuperfrogUserDtoConverter, SuperfrogUserDtoToSuperfrogUserConverter superfrogUserDtoToSuperfrogUserConverter) {
        this.superfrogUserService = superfrogUserService;
        this.superfrogUserToSuperfrogUserDtoConverter = superfrogUserToSuperfrogUserDtoConverter;
        this.superfrogUserDtoToSuperfrogUserConverter = superfrogUserDtoToSuperfrogUserConverter;
    }

    @GetMapping
    public Result findAllUsers() {
        List<SuperfrogUser> foundSuperfrogUsers = this.superfrogUserService.findAll();

        // Convert foundUsers to a list of UserDtos.
        List<SuperfrogUserDto> userDtos = foundSuperfrogUsers.stream()
                .map(this.superfrogUserToSuperfrogUserDtoConverter::convert)
                .collect(Collectors.toList());

        // Note that UserDto does not contain password field.
        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);
    }

    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Integer userId) {
        SuperfrogUser foundSuperfrogUser = this.superfrogUserService.findById(userId);
        SuperfrogUserDto userDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(foundSuperfrogUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", userDto);
    }


    @PostMapping
    public Result addUser(@Valid @RequestBody SuperfrogUser newSuperfrogUser) {
        SuperfrogUser savedSuperfrogUser = this.superfrogUserService.save(newSuperfrogUser);
        SuperfrogUserDto savedUserDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(savedSuperfrogUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    // We are not using this to update password, need another changePassword method in this class.
    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable Integer userId, @Valid @RequestBody SuperfrogUserDto userDto) {
        SuperfrogUser update = this.superfrogUserDtoToSuperfrogUserConverter.convert(userDto);
        SuperfrogUser updatedSuperfrogUser = this.superfrogUserService.update(userId, update);
        SuperfrogUserDto updatedUserDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(updatedSuperfrogUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        this.superfrogUserService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PutMapping("/{username}/disable")
    public Result disableUser(@PathVariable String username) {
        SuperfrogUser updatedSuperfrogUser = this.superfrogUserService.disableUser(username);
        SuperfrogUserDto updatedUserDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(updatedSuperfrogUser);
        return new Result(true, StatusCode.SUCCESS, "Disable User Success", updatedUserDto);
    }

    @PutMapping("/{username}/enable")
    public Result enableUser(@PathVariable String username) {
        SuperfrogUser updatedSuperfrogUser = this.superfrogUserService.enableUser(username);
        SuperfrogUserDto updatedUserDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(updatedSuperfrogUser);
        return new Result(true, StatusCode.SUCCESS, "Enable User Success", updatedUserDto);
    }

}