package edu.tcu.cs.superfrogscheduler.request;

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
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;

    private final RequestToRequestDtoConverter requestToRequestDtoConverter;

    private final RequestDtoToRequestConverter requestDtoToRequestConverter;

    public RequestController(RequestService requestService,
                             RequestToRequestDtoConverter requestToRequestDtoConverter,
                             RequestDtoToRequestConverter requestDtoToRequestConverter) {
        this.requestService = requestService;
        this.requestToRequestDtoConverter = requestToRequestDtoConverter;
        this.requestDtoToRequestConverter = requestDtoToRequestConverter;
    }

    @GetMapping("/{requestId}")
    public Result findRequestById(@PathVariable String requestId) {
        Request foundRequest = this.requestService.findById(requestId);
        RequestDto requestDto = this.requestToRequestDtoConverter.convert(foundRequest);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", requestDto);
    }

    @GetMapping
    public Result findAllRequests() {
        List<Request> foundRequests = this.requestService.findAll();
        // Convert foundRequests to a list of requestDtos
        List<RequestDto> requestDtos = foundRequests.stream()
                .map(this.requestToRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", requestDtos);
    }

    @PostMapping
    public Result addRequest(@Valid @RequestBody RequestDto requestDto) {
        // Convert SuperfrogDto to Superfrog
        Request newRequest = this.requestDtoToRequestConverter.convert(requestDto);
        Request savedRequest = this.requestService.save(newRequest);
        RequestDto savedRequestDto = this.requestToRequestDtoConverter.convert(savedRequest);
        return new Result(true,
                StatusCode.SUCCESS,
                "Add Success",
                savedRequestDto);
    }

    @PutMapping("/{requestId}")
    public Result updateRequest(@PathVariable String requestId, @Valid @RequestBody RequestDto requestDto) {
        Request update = this.requestDtoToRequestConverter.convert(requestDto);
        Request updatedRequest = this.requestService.update(requestId, update);
        RequestDto updatedRequestDto = this.requestToRequestDtoConverter.convert(updatedRequest);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedRequestDto);

    }

    @DeleteMapping("/{requestId}")
    public Result deleteRequest(@PathVariable String requestId) {
        this.requestService.delete(requestId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
