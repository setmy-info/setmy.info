
const SMI_DEFAULT_FUNCTION = function (index, control) {
    console.log("Control: ", index, control);
};
const SMI_CONTROLS_PREFIX = "smiControls";
const SMI_BUTTON_CONTROLS_PREFIX = SMI_CONTROLS_PREFIX + "Button";
const SMI_TEXTAREA_CONTROLS_PREFIX = SMI_CONTROLS_PREFIX + "TextArea";

const smiControls = {
    controls: Array.from({length: 10}, () => ({func: SMI_DEFAULT_FUNCTION, textarea: null})),
    setFunction: function (index, func) {
        this.controls[index].func = func;
    },
    execute: function (index) {
        const control = this.controls[index];
        control.func(index, control);
    },
    init: function () {
        const that = this;
        this.controls.forEach((control, index) => {
            const textarea = document.createElement('textarea');
            textarea.id = SMI_TEXTAREA_CONTROLS_PREFIX + index;
            control.textarea = textarea;
            document.body.appendChild(textarea);

            const button = document.createElement('button');
            button.textContent = 'Button ' + index;
            button.id = SMI_BUTTON_CONTROLS_PREFIX + index;
            button.addEventListener('click', () => {
                that.execute(index);//is this working?
            });

            control.button = button;
            document.body.appendChild(button);
        });
    }
};

smiControls.init();
