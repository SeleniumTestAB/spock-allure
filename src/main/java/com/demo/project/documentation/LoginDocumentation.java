package com.demo.project.documentation;

import static j2html.TagCreator.b;
import static j2html.TagCreator.body;
import static j2html.TagCreator.h4;
import static j2html.TagCreator.html;
import static j2html.TagCreator.img;
import static j2html.TagCreator.p;

public class LoginDocumentation {

    private String loginAsAdminDocumentation() {
        return html(body(
                h4(b("Test aim")),
                p("Login as admin correctly"),
                img().withStyle("width: 50%").withSrc("data:image/jpg;base64, "+ImageEncoder.encodeImageToBase64("loginForm.JPG"))

        )).render();
    }


}
