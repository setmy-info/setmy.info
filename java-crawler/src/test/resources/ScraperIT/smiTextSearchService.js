const smiTextArea = document.createElement('textarea');

const smiTextSearchService = {

    parseAllTexts: function () {
        const that = this,
            allNodes = this.findAllTextNodes();
        const result = [];
        allNodes.forEach(function (nodeData) {
            const parentNode = nodeData.parentNode;
            const textNode = nodeData.textNode;
            const nodeName = parentNode.nodeName.toLowerCase();
            const textElementProperties = {
                nodeName: nodeName,
                text: textNode.nodeValue.trim(),
                url: nodeName === "a" ? parentNode.href : null,
                y: parentNode.offsetTop, //node.getBoundingClientRect();
                x: parentNode.offsetLeft,
                width: parentNode.offsetWidth,
                height: parentNode.offsetHeight,
                //----
                color: getComputedStyle(parentNode).color,
                fontSize: getComputedStyle(parentNode).fontSize,
                fontStyle: getComputedStyle(parentNode).fontStyle,
                bold: getComputedStyle(parentNode).fontWeight === 'bold',
                italic: getComputedStyle(parentNode).fontStyle === 'italic',
                backgroundColor: getComputedStyle(parentNode).backgroundColor,
                location: that.getLocation(parentNode)
            };
            result.push(textElementProperties);
        });
        return result;
    },

    findAllTextNodes: function (node) {
        const textNodes = [], that = this;

        function find(node) {
            if (that.isTextNode(node) && that.isAcceptableNode(node)) {
                textNodes.push({textNode: node, parentNode: node.parentNode});
            } else {
                for (const childNode of node.childNodes) {
                    find(childNode);
                }
            }
        }

        find(node || document.documentElement);
        return textNodes;
    },

    isTextNode: function (node) {
        return node.nodeType === Node.TEXT_NODE;
    },

    isAcceptableNode: function (textNode) {
        const nodeName = textNode.parentNode.nodeName.toLowerCase();
        return nodeName !== "script" && nodeName !== "head" && nodeName !== "script";
    },

    getLocation: function (element) {
        const path = [];
        while (element.parentNode) {
            let childIndex = Array.from(element.parentNode.children).indexOf(element);
            let childTag = element.tagName.toLowerCase();
            path.push({index: childIndex, tag: childTag});
            element = element.parentNode;
        }
        return path.reverse().map(item => `${item.index}:${item.tag}`).join(';');
    }
};

smiTextArea.id = "smiTextArea";
const jsonString = JSON.stringify(smiTextSearchService.parseAllTexts());
smiTextArea.value = jsonString;
console.log("String: ", smiTextArea.value);
document.body.appendChild(smiTextArea);
