# Swagger
기본적인 Swagger 생성 방식 입니다.
````
* Swagger 버전에 따른 UI URL 체크* 
2.X.X  :  http://localhost:8080/swagger-ui.html
3.X.X  :  http://localhost:8080/swagger-ui/index.html
````
### 라이브러리 추가
```` yml
....
dependencies {
    ....
    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui
    implementation 'io.springfox:springfox-swagger-ui:3.0.0'
    // https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
    implementation 'io.springfox:springfox-boot-starter:3.0.0'
}
....
````
#### 코드 기능들
- @EnableSwagger2 → Swagger2 활성화
- new Docket(DocumentationType.SWAGGER_2).select() → API 에 사용되는 builder 생성 입니다.
- apis() → RequestHandlerSelectors 를 이용 api 스펙이 작성되는 즉, Controller 부 basepackage를 지정 합니다.
- paths() → PathSelectors를 이용 apis() 에 선택된 스팩들중 특정 path 조건을 다시 필터링 합니다.
- ApiInfo → Swagger에 사용되는 title, description, version 등이 사용이됩니다.

- @ApiOperation → 어노테이션을 API에 추가해 API에 대한 주석을 달 수 있음(옵션)
- @ApiResponses → response 상태 값에 따라 설명을 적어놓는 있다
- @ApiParam → @RequestParam 시 필요한 부분을 테스트 할수 있도록 적어 놓은 부분
- @ApiModelProperty → Dto 들의 값(JSON) 표현시 사용되는 일종의 값
