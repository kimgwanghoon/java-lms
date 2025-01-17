package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.courses.type.SessionStatus;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus status;
    private Price price;
    private Image image;

    public Session(String title, SessionPeriod sessionPeriod, Price price, SessionStatus status, Image image) {
        this(null, title, sessionPeriod, price, status, image);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, Price price, SessionStatus status, Image image) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree() {
        return price.isFree();
    }

    public void addParticipant(int money, NsUser nsUser) {
        validateRecruiting();
        price.addParticipant(money, nsUser);
    }

    public void validateRecruiting() {
        if (status != SessionStatus.RECRUIT) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public int nowParticipants() {
        return price.nowParticipants();
    }
}
