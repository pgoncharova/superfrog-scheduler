package edu.tcu.cs.superfrogscheduler.spiritDirector.superfrog;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogDtoToSuperfrogConverter;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/superfrogs")
public class SuperfrogController {
    private final SuperfrogService superfrogService;

    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;

    private final SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter;

    public SuperfrogController(SuperfrogService superfrogService,
                               SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter,
                               SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter) {
        this.superfrogService = superfrogService;
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
        this.superfrogDtoToSuperfrogConverter = superfrogDtoToSuperfrogConverter;
    }

    @GetMapping("/{superfrogEmail}")
    public Result findSuperfrogByEmail(@PathVariable String superfrogEmail) {
        Superfrog foundSuperfrog = this.superfrogService.findById(superfrogEmail);
        SuperfrogDto superfrogDto = this.superfrogToSuperfrogDtoConverter.convert(foundSuperfrog);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", superfrogDto);
    }

    @GetMapping
    public Result findAllSuperfrogs() {
        List<Superfrog> foundSuperfrogs = this.superfrogService.findAll();
        // Convert foundSuperfrogs to a list of superfrogDtos
        List<SuperfrogDto> superfrogDtos = foundSuperfrogs.stream()
                .map(this.superfrogToSuperfrogDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", superfrogDtos);
    }

    @PostMapping
    public Result addSuperfrog(@Valid @RequestBody SuperfrogDto superfrogDto) {
        // Convert SuperfrogDto to Superfrog
        Superfrog newSuperfrog = this.superfrogDtoToSuperfrogConverter.convert(superfrogDto);
        Superfrog savedSuperfrog = this.superfrogService.save(newSuperfrog);
        SuperfrogDto savedSuperfrogDto = this.superfrogToSuperfrogDtoConverter.convert(savedSuperfrog);
        return new Result(true,
                StatusCode.SUCCESS,
                "Add Success",
                savedSuperfrogDto);
    }

    @PutMapping("/{superfrogEmail}")
    public Result updateSuperfrog(@PathVariable String superfrogEmail, @Valid @RequestBody SuperfrogDto superfrogDto) {
        Superfrog update = this.superfrogDtoToSuperfrogConverter.convert(superfrogDto);
        Superfrog updatedSuperfrog = this.superfrogService.update(superfrogEmail, update);
        SuperfrogDto updatedSuperfrogDto = this.superfrogToSuperfrogDtoConverter.convert(updatedSuperfrog);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedSuperfrogDto);

    }

    @DeleteMapping("/{superfrogEmail}")
    public Result deleteSuperfrog(@PathVariable String superfrogEmail) {
        this.superfrogService.delete(superfrogEmail);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
