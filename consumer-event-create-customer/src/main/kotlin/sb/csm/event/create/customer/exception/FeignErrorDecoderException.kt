package sb.uma.exception

import feign.Response
import feign.codec.ErrorDecoder


class FeignErrorDecoderException(
    private val errorDecoder: ErrorDecoder = ErrorDecoder.Default()
) : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response): Exception {
        return FeignApiException("Integration has failed. integration_api_status= ${response.status()}")
    }
}