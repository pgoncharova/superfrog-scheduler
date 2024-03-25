package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogDtoToSuperfrogConverter;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/superfrogs")
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

    @GetMapping("/{email}")
    public Result findSuperfrogByEmail(@PathVariable String email) {
        Superfrog foundSuperfrog = this.superfrogService.findById(email);
        SuperfrogDto superfrogDto = this.superfrogToSuperfrogDtoConverter.convert(foundSuperfrog);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", superfrogDto);
    }

}
