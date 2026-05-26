package com.bfhl.service;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;

public interface IBfhlService {

    /**
     * Processes the incoming data array and returns the classified response.
     *
     * @param request the incoming request containing the data list
     * @return a fully populated {@link BfhlResponse}
     */
    BfhlResponse processData(BfhlRequest request);
}
