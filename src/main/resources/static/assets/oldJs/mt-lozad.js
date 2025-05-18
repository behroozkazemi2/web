function initMtLozad() {
    const observer = lozad('.lozad', {
        enableAutoReload: true,// it will reload the new image when validating attributes changes
        load: function (el) {
            // Custom implementation to load an element
            el.src = el.dataset.src;
        }
    });
    observer.observe();
}