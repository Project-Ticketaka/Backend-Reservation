# 👁️‍🗨️ 개요

예약되는 공연의 정보에 대해 추가, 삭제를 할 수 있으며 관련 정보를 메일 서버로 요청하는 API 서버입니다.

<br>

# 🌆 기능 및 아키텍처

<br>

<details>
<summary> 📝 API 명세서 </summary>
<div markdown="1">       

- [reservation](#reservation)
- [reservationList](#reservationList)
- [reservationInfo](#reservationInfo)
- [deleteReservation](#deleteReservation)


## reservation
결제한 공연의 예매 공연정보를 생성

<br>

### URL

- POST `/reservation/create`
- Headers
    - Authorization: login token

### 요청 예시

```json
{
    "performanceId": "PF132236",
    "performanceTitle": "오페라 유령3",
	"reservationTicketCount": 2,
	"reservationDate": "2023-01-15",
	"reservationTime": "18:30",
	"reservationPrice": 100000,
	"reservationPoster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF209894_230117_133614.gif",
    "memberEmail": "yn15@naver.com"
}
```

### 응답 예시

- ✅ 성공

    ```json
    {
        "code": 200,
        "description": "성공"
    }
    ```

<br>

- ⚠️ 실패
    
    ```json
    {
        "code": 500,
        "description": "Rabbitmq와 통신 할 수 없습니다."
    }
    ```

## reservationList
유저가 예매한 내역 목록을 조회

<br>

### URL

- GET `/reservation/lists`
- Headers
    - Authorization: login token


### 응답 예시

- ✅ 성공

    ```json
    {
        "code": 200,
        "description": "성공",
        "data": [
            {
                "reservationId": 17,
                "reservationTicketCount": 2,
                "performanceTitle": "오페라 유령2",
                "reservationDate": "2023-01-15",
                "reservationTime": "18:30",
                "reservationPrice": 100000,
                "reservationTotalPrice": 200000,
                "reservationPoster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF209894_230117_133614.gif",
                "reservationDeleted": "N",
                "reservationCreateAt": "2023-03-14T17:37:10"
            },...
        ]
    }
    ```

<br>

- 실패
    - ⚠️ 조회 데이터 없음
        ```json
        {
            "code": 202,
            "description": "예약 정보가 없습니다."
        }
        ```

## reservationInfo
예매 목록 중에서 선택한 예매 내역의 상세 정보 조회

<br>

### URL

- GET `/reservation/list/:rsv_id`
- Headers
    - Authorization: login token

### 응답 예시

- ✅ 성공

    ```json
    {
        "code": 200,
		"description": "성공",
        "data": {
            "reservationId": 1,
            "reservationTicketCount": 2,
            "performanceTitle": "오페라 유령",
            "reservationDate": "2023-01-15",
            "reservationTime": "18:30",
            "reservationPrice": 100000,
            "reservationTotalPrice": 200000,
            "reservationPoster": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF209894_230117_133614.gif",
            "reservationDeleted": "N",
            "reservationCreateAt": "2023-02-28T11:27:57"
        }
    }
    ```

<br>

- 실패
    - ⚠️ 조회 데이터 없음
    ```json
    {
        "code": 202,
        "description": "예약 정보가 없습니다."
    }
    ```


## deleteReservation
상세 예매 내역에서 예매 취소 버튼을 통해 정보 삭제

<br>

### URL

- DELETE `/reservation/delete/:rsv_id`
- Headers
    - Authorization: login token

### 응답 예시

- ✅ 성공

    ```json
    {
        "code": 200,
        "description": "성공"
    }
    ```

</div>
</details>

<br>

![](https://user-images.githubusercontent.com/80504636/231212263-da43c169-c98e-4a56-92a4-edd3d87c7e2b.png)

<br>

## 💽 ERD

<br>

![ERD](https://user-images.githubusercontent.com/68692931/231386438-ec59c3f7-b69b-4af3-bf90-2b94826297e9.png)

<br>

- Reservation Table - 예약된 공연 정보
- trg_deletedReservation TBL Trigger- 예약 취소시 Delete_Reservation Table에 데이터 삽입
- Delete_Reservation Table - 취소된 예약 정보
- Unit_Reservation View - 예매내역 조회시 Reservation Table, Delete_Reservation Table을 모두 보여주는 View

<br>

## 📧 메일 요청 ( Spring )

- `/reservation/create` 로 요청된 예매 정보 객체를 RabbitTemplate를 통해 Json형태로 Convert해 정해진 `exchange`, `routingkey`, `queue`로 전송되게 된다.

<br>

- `ConnectionFactory connectionFactory()` / `RabbitTemplate rabbitTemplate` / `MessageConverter messageConverter()` 를 Bean으로 관리해 요청시마다 RabbiMQ에게 메세지를 전송하는 Producer 역할을 함

    ```java
    /* 객체 형태의 데이터를 직렬화 */
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /* RabbiMQ에 전송할 메시지 변환 및 생성 */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /* host, port, username, password를 이용해 RabbiMQ와 Connection을 생성하는 Bean*/
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    ```
<br>

## 📮 메일 전송 ( Python )

- Python의 pika라이브러리 사용
    - AMPQ 프로토콜을 사용하며 네트워크 프레임워크와 독립적으로 동작한다.

    - 양방향 RPC프로토콜이며 IOLOOP가 실행되므로 네트워크 프레임워크를 따로 사용하지 않았다.
    
    <br>

- RabbiMQ와 Connection을 맺고 설정된 Queue를 구독한다.

<br>

- Consumer의 역할을하고 Queue에 대기하는 메시지를 소비하게 된다.
    - `on_message` 콜백 함수를 이용해 Queue 메시지를 소비하여 html을 생성한다.
    
        ```python
        # 해당 함수를 통해 이메일을 발송하고 정상적으로 처리시 RabbiMQ에 ack를 보냄
        def on_message(to_channel, method_frame, header_frame, body):
            body = body.decode()
            data = json.loads(body)
            make_qr(data['reservationId'])
        
            logger.log(logging.DATA, data)
            send(data['memberEmail'], data)
            to_channel.basic_ack(delivery_tag=method_frame.delivery_tag)
        ```
    <br>
    
- 소비하는 메시지는 예약된 공연정보가 있고 이를 통해 html_tempalte를 생성해 요청된 메일로 전송한다.

<br>

- Jinja2의 `Environment` , `PackageLoader` , `select_autoescape` 를 이용해 지정된 html template 수신 한 메시지를 삽입해 QR coder를 포함한 Ticket을 발송하는 형태이다.
    
    ```python
    env = Environment(
        loader=PackageLoader('consumer_mail', 'templates'),
        autoescape=select_autoescape(['html', 'xml'])
    )
    
    html_template = 'ticket.html'
    
    # 지정된 html파일에 인자로 들어온 data를 rendering
    def template(data):
        t = env.get_template(html_template)
        return t.render(data=data)
    ```

<br>
    
- reservationID를 담고 있는 QR code를 이미지 형태로 생성한다.
    
    - gmail 등 특정 메일 플랫폼은 base64를 지원하지 않아 png형태의 파일을 생성하고 이를 메일 메시지에 attach후 전송한다.
    
        ```python
        msg.attach(html)
        
        assert os.path.isfile("./qrcode.png"), 'image file does not exist.'
        with open("./qrcode.png", 'rb') as img_file:
            mime_img = MIMEImage(img_file.read())
            mime_img.add_header('Content-ID', '<' + 'qrcode' + '>')
        msg.attach(mime_img)
        
        ...
        
        qr = qrcode.QRCode(
                version=None,
                error_correction=qrcode.constants.ERROR_CORRECT_L,
                box_size=10,
                border=0,
                image_factory=None,
            )
        
        qr.add_data("reservation_id: " + str(reservation_id))
        qr.make(fit=True)
        qr_img = qr.make_image()
        qr_img.save("./qrcode.png")
        ```
    
<br>

- 회원가입시 필요한 이메일 인증을 위한 인증코드 또한 메일로 발송하게 된다.

<br>

# 🪵 개발환경

- Java 11

- Spring Boot
    - Spring Data JPA
    
    - Spring Boot AMPQ

- MariaDB:10.3

- RabbiMQ 3.11.10

- Python 3.8.6

<br>


# 💬 회고

## 프로젝트 진행 시 주안점

- 초기에 Spring에서 메일 발송 예정이였음
    - 하지만 Thymeleaf 버그, API 서버 부하 등으로 메세징 큐 사용해 메일 발송

- Spring에서 RabbitMQ 정보 생성
    - 이후 RabbitMQ 노드를 초기화 할때 생성하도록 변경

- 예약 취소 데이터의 일관성 유지를 위해 트리거 생성

<br>

## 한계점 및 개선 사항

- 파이썬에서 QR코드의 직접적 이미지 생성하는 방법 이외의 방법 마련

- Consumer의 상태를 파악하고 가중치를 부여하는 방법 마련

- Queue의 적절합 Disk/RAM 타입 설정

- 사설 메일 서버 구성

- Flask환경에서 Pika라이브러리 사용방법 모색
