function offset(element) {
    let left = element.offsetLeft + element.offsetParent.offsetLeft
    let top = element.offsetTop + element.offsetParent.offsetTop
    return { left, top };
}

document.addEventListener("DOMContentLoaded", function(event) {

    const wrapper = document.querySelector('.task-image--wrapper')
    const SCALABLE_COEFFICIENT = 1.25
    const WINDOW_SIZE = wrapper.clientWidth

    function getRadius() {
        let radius = document.getElementById('form:r').value;
        if (isNaN(parseFloat(radius))) {
            throw new Error('Для того, чтобы взаимодействовать с элементом, необходимо ввести радиус')
        }
        if (parseFloat(radius) < 1 || parseFloat(radius) > 4) {
            throw new Error('Радиус должен быть в указанном диапазоне')
        }
        return radius
    }

    let addPoint = (x, y) => {
        let dot = document.createElement('div')
        dot.classList.add('task-image--dot')
        dot.style.left = x + 'px'
        dot.style.top = y + 'px'
        document.querySelector('.task-image--wrapper').append(dot)
    }
    let addPointStatus = (x, y, status) => {
        let dot = document.createElement('div')
        dot.classList.add('task-image--dot')
        if (status) dot.classList.add('_active')
        else dot.classList.add('_inactive')
        dot.style.left = x + 'px'
        dot.style.top = y + 'px'
        document.querySelector('.task-image--wrapper').append(dot)
    }

    setInterval(() => {
        if (document.querySelectorAll('.task-image--dot').length > 100) return
        let radius = getRadius();
        let dots = document.querySelectorAll('.dot')
        console.log(dots)
        dots.forEach((el) => {
            let node = document.createElement('div')
            node.classList.add('.task-image--dot')
            let x = parseFloat(el.querySelector('.dot-x').innerHTML)
            let y = parseFloat(el.querySelector('.dot-y').innerHTML)
            let status = el.querySelector('.dot-status').innerHTML === 'true'
            let positionX = (500 / 2) * (x / (1.25 * radius) + 1)
            let positionY = (500 / 2) * (-y / (1.25 * radius) + 1)
            addPointStatus(positionX, positionY, status)
        })
    }, 1000)

    document.addEventListener('click',function(e){
        if (e.target && e.target.matches('.task-image--wrapper')) {
            let windowPositionX = e.pageX - offset(document.querySelector('.task-image--wrapper')).left
            let windowPositionY = e.pageY - offset(document.querySelector('.task-image--wrapper')).top
            try {
                let radius = getRadius();
                let x = (windowPositionX / (WINDOW_SIZE / 2) - 1) * SCALABLE_COEFFICIENT * radius
                let y = (-windowPositionY / (WINDOW_SIZE / 2) + 1) * SCALABLE_COEFFICIENT * radius

                document.getElementById("form2:x1").value = x.toFixed(3)
                document.getElementById("form2:y1").value = y.toFixed(3)
                document.getElementById("form2:r1").value = parseFloat(radius).toFixed(3)
                document.getElementById("form2:update").click()

                console.log(windowPositionX, windowPositionY, x, y)

                let jsfCommandLink = document.getElementById("form1:link");
                jsfCommandLink.click();

                addPoint(windowPositionX, windowPositionY)

                //swal("Точка успешно добавлена")

            } catch (e) {
                swal("Ошибка", e.message, "error");
            }
        }
    });





});